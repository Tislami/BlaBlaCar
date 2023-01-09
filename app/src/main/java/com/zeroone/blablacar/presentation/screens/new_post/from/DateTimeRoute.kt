package com.zeroone.blablacar.presentation.screens.new_post.from

import android.app.TimePickerDialog
import android.util.Log
import android.widget.TimePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.color.KalendarThemeColor
import com.himanshoe.kalendar.component.day.config.KalendarDayColors
import com.himanshoe.kalendar.model.KalendarType
import com.himanshoe.kalendar.ui.firey.KalendarFirey
import com.zeroone.blablacar.presentation.screens.new_post.NewPostState
import com.zeroone.blablacar.presentation.screens.new_post.NewPostTopAppBar
import com.zeroone.blablacar.presentation.screens.new_post.NewPostViewModel
import com.zeroone.blablacar.presentation.screens.new_post.navigation.NewPostRoutes
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import java.util.*

@Composable
fun DateTimeRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    newPostViewModel: NewPostViewModel,
) {
    val scaffoldState = rememberScaffoldState()
    val postState = newPostViewModel.newPostState.value

    DateTimeScreen(
        modifier = modifier,
        scaffoldState = scaffoldState,
        postState = postState,
        onNavigationClick = { navController.popBackStack() },
        onActionClick = { navController.navigate(NewPostRoutes.PersonCount.route) },
        onSetTime = newPostViewModel::setTime,
        onSetDate = newPostViewModel::setDate,
    )
}

@Composable
private fun DateTimeScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    postState: NewPostState,
    onNavigationClick: () -> Unit,
    onActionClick: () -> Unit,
    onSetTime: (String) -> Unit,
    onSetDate: (String) -> Unit,
) {

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            NewPostTopAppBar(
                navigationIcon = Icons.Default.ArrowBack,
                actionButtonIcon = Icons.Default.ArrowForward,
                onNavigationClick = onNavigationClick,
                onActionButtonClick = onActionClick,
                actionButtonVisible = postState.date.isNotEmpty() && postState.time.isNotEmpty(),
                isLoading = false
            )
        },
        content = { padding ->
            DateTimeScreen(
                modifier = modifier.padding(padding),
                postState = postState,
                onSetTime = onSetTime,
                onSetDate = onSetDate
            )
        }
    )

}


@Composable
private fun DateTimeScreen(
    modifier: Modifier = Modifier,
    postState: NewPostState,
    onSetTime: (String) -> Unit,
    onSetDate: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        Text(text = "Ne zaman yolculuk yapacaksın")

        Surface(
            shape = MaterialTheme.shapes.large,
            elevation = 8.dp,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(vertical = 16.dp)
                .height(250.dp),
            color = MaterialTheme.colors.background
        ) {

            KalendarFirey(
                onCurrentDayClick = { kalendarDay, _ ->
                    val date = "${kalendarDay.localDate.dayOfYear} / ${kalendarDay.localDate.month} / ${kalendarDay.localDate.year} "
                    onSetDate(date)
                },
                takeMeToDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                kalendarDayColors = KalendarDayColors(
                    textColor = MaterialTheme.colors.onBackground,
                    selectedTextColor = MaterialTheme.colors.primary
                ),
                kalendarThemeColor = KalendarThemeColor(
                    backgroundColor = MaterialTheme.colors.background,
                    dayBackgroundColor = MaterialTheme.colors.onBackground,
                    headerTextColor = MaterialTheme.colors.primary
                )
            )
        }

        TimeField(time = postState.time, setTime = onSetTime)
    }
}


@Composable
private fun TimeField(
    time: String,
    setTime : (String)->Unit,
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val hour: Int = calendar.get(Calendar.HOUR)
    val minute: Int = calendar.get(Calendar.MINUTE)

    calendar.time = Date()

    val timePickerDialog = TimePickerDialog(
        context,
        { _: TimePicker, _hourOfDay: Int, _minute: Int ->
            setTime("$_hourOfDay : $_minute")
        }, hour, minute, true
    )

    Surface(
        shape = MaterialTheme.shapes.large,
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { timePickerDialog.show() },
        color = MaterialTheme.colors.background
    ) {
        Text(
            text = time,
            textAlign = TextAlign.Center,
            fontSize = 36.sp
        )
    }
}
