import com.example.applicationdesign.components.DesignScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.ViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            DrawIcon()
//            ClipCutDesign()
//            TextFieldDemo()
//            ButtonDemo()

            DesignScreen()
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun ButtonDemo(){
    var bgColor by remember {
        mutableStateOf(Color.White)
    }
    var isChecked by remember {
        mutableStateOf(true)
    }
    var sliderValue by remember {
        mutableFloatStateOf(0f)
    }

    var mExpanded by remember { mutableStateOf(false) }

    // Create a list of cities
    val mCities = listOf("Delhi", "Mumbai", "Chennai", "Kolkata", "Hyderabad", "Bengaluru", "Pune")

    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column(modifier = Modifier
        .fillMaxSize()
        .background(bgColor)) {

        Button(onClick = {
            bgColor = if (bgColor == Color.Green) Color.White else Color.Green
        }) {
            Text("Filled Button",)
        }
        Checkbox(checked = isChecked,
            onCheckedChange = { isChecked = it},
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Blue,
                checkmarkColor = Color.White
            ))
        Switch(checked = isChecked, onCheckedChange = {isChecked = it})
        Slider(value = sliderValue, onValueChange = {sliderValue = it})
        Text(sliderValue.toString())

        Column(Modifier.padding(20.dp)) {

            // Create an Outlined Text Field
            // with icon and not expanded
            OutlinedTextField(
                value = mSelectedText,
                onValueChange = { mSelectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        // This value is used to assign to
                        // the DropDown the same width
                        mTextFieldSize = coordinates.size.toSize()
                    },
                label = {Text("Label")},
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { mExpanded = !mExpanded })
                }
            )

            // Create a drop-down menu with list of cities,
            // when clicked, set the Text Field text as the city selected
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .width(300.dp)
            ) {
                mCities.forEach { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = label
                        mExpanded = false
                    }, text = { Text(label) })
                }
            }
        }
    }
}


@Composable
fun TextFieldDemo(){
    var value by remember { mutableStateOf("") }
    Column (modifier = Modifier.padding(10.dp)){
        OutlinedTextField(value = value,
            onValueChange = {value = it},
            placeholder = {Text("Enter Name")},
            shape = RoundedCornerShape(15.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Green,
                focusedBorderColor = Color.Green
            )

        )
        Text("My Name is: $value")
    }
}

class NameViewModel : ViewModel(){
    private var username by mutableStateOf("")
    fun updateUsername(input: String){
        username = input
    }
}

@Composable
fun ClipCutDesign(){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        val triangleShape = GenericShape {size, _ ->
            moveTo(size.width.times(0.5f), 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
        }

        val semiCircleShape = GenericShape {size, _ ->
            val w = size.width
            val h = size.height
            moveTo(w.times(0.5f), h)
            arcTo(Rect(Offset(
                w.times(0.5f),
                h),
                radius = w * 0.5f),
                forceMoveTo = false,
                sweepAngleDegrees = -180f,
                startAngleDegrees = 0f)
            close()
        }

        Box {
            Box(modifier = Modifier
                .height(300.dp)
                .width(300.dp)

                .background(Color.Black),
            )
            Box(modifier = Modifier
                .height(300.dp)
                .width(300.dp)
                .clip(CustomSemiCircleShape())
                .background(Color.Blue),
            )
        }
    }
}

class CustomSemiCircleShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val w = size.width
            val h = size.height
            moveTo(w.times(0.5f), h)
            arcTo(Rect(Offset(
                w.times(0.5f),
                h),
                radius = w * 0.5f),
                forceMoveTo = false,
                sweepAngleDegrees = -180f,
                startAngleDegrees = 0f)
            close()
        }
        return Outline.Generic(path)
    }
}



@Composable
fun DrawIcon(){
    Canvas(modifier = Modifier
        .size(100.dp)
        .padding(16.dp)) {
//        drawRect(color = Color.Black,
//            size = Size(250f, 250f),
//            topLeft = Offset(size.width * -0.17f,
//                size.height * -0.16f)
//        )
//        drawRoundRect(color = Color.White,
//            cornerRadius = CornerRadius(10f, 10f),
//            size = Size(220f, 220f),
//            topLeft = Offset(size.width * -0.09f,
//                size.height * -0.085f)
//        )
//        drawCircle(color = Color.Red, radius = 100f)
//        drawCircle(color = Color.Green, radius = 80f)
//        drawCircle(color = Color.Yellow, radius = 50f)
//        drawCircle(color = Color.White, radius = 20f)
        
//        val w = size.width
//        val h = size.height
//        val path = Path().apply {
//            moveTo(w.times(0.5f),
//                h.times(0.5f))
//            lineTo(w.times(0.2f),
//                h.times(0.2f))
//            lineTo(w.times(0.8f), h.times(0.2f))
//            close()
//        }
//        val path2 = Path().apply {
//            moveTo(w.times(0.5f),
//                h.times(0.5f))
//            lineTo(w.times(0.2f), h.times(0.8f))
//            lineTo(w.times(0.8f), h.times(0.8f))
//            close()
//        }
//        drawPath(path = path,
//            color = Color.Red,
//            style = Fill)
//        drawPath(path = path,
//            color = Color.Black,
//            style = Stroke(width = 5f))
//        drawPath(path = path2,
//            color = Color.Green,
//            style = Fill)
//        drawPath(path = path2,
//            color = Color.Black,
//            style = Stroke(width = 5f))

//        val w = size.width
//        val h = size.height
//        val path = Path().apply {
//            moveTo(w.times(0.5f), h.times(0.5f))
//            lineTo(w.times(0.2f), h.times(0.7f))
//            quadraticBezierTo(w.times(0.5f), h.times(1f), w.times(0.8f), h.times(0.7f))
//            close()
//        }
//        val path2 = Path().apply {
//            moveTo(w.times(0.5f), h.times(0.5f))
//            lineTo(w.times(0.2f), h.times(0.3f))
//            quadraticBezierTo(w.times(0.5f), h.times(0f), w.times(0.8f), h.times(0.3f))
//            close()
//        }
//        drawPath(path = path2, color = Color.Red, style = Fill)
//        drawPath(path = path, color = Color.Green, style = Fill)
//        drawPath(path = path, color = Color.Black, style = Stroke(width = 3f))
//        drawPath(path = path2, color = Color.Black, style = Stroke(width = 3f))

//        val w = size.width
//        val h = size.height
//        drawArc(color = Color.Black,
//            startAngle = 90f,
//            sweepAngle = -90f,
//            useCenter = false,
//            size= Size(w.times(0.8f),
//                h.times(0.8f)),
//            style = Stroke(width = 3f)
//            )
    }
}
