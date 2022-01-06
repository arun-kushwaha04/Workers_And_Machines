package com.jetpack.machineandworker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetpack.machineandworker.ui.theme.MachineAndWorkerTheme

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MachineAndWorkerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color(0xFF101010)) {
//                    MachineGrid()
//                    WorkerLayout()
                    //here is the navigation of the app
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home"){
                        composable("home"){ MainScreen(navController)}
                        composable("machine"){ MachineGrid()}
                        composable("worker"){ WorkerLayout()}
                    }
                }
            }
        }
    }
}


@Composable
fun MainScreen(
    navController: NavController
){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        OutlinedButton(
            onClick = { navController.navigate("machine") },
            modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(Color(0xFFCCCCCC))
        ) {
            Text("Manage Machines", color = Color(0xFF101010))
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))
        OutlinedButton(
            onClick = { navController.navigate("worker") },
            modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(Color(0xFFCCCCCC))
        ) {
            Text("Monitor Workers", color = Color(0xFF101010))
        }
    }

}

data class Machine(
    val name: String,
    val workerName: String,
)

val machineList: List<Machine> = listOf(
    Machine("Machine1","Ramesh"),
    Machine("Machine2","Suresh"),
    Machine("Machine3","Keshav"),
    Machine("Machine4","Arvind"),
    Machine("Machine5","Amit"),
    Machine("Machine6","Binod"),
    Machine("Machine7","Bimlesh"),
    Machine("Machine8","Nagesh"),
    Machine("Machine9","Bablu"),
    Machine("Machine10","Shyam"),
)

val workerList: List<String> = listOf(
    "Ramesh",
    "Suresh",
    "Keshav",
    "Arvind",
    "Amit",
    "Binod",
    "Bimlesh",
    "Nagesh",
    "Bablu",
    "Shyam",
)

val workerStatus = listOf(
    WorkerStatus("Ramesh", listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
    WorkerStatus("Ramesh", listOf(1,2,3,4)),
)

data class WorkerStatus(
    val name: String,
    val machineAllotted: List<Int>
)

@ExperimentalFoundationApi
@Composable
fun MachineGrid(
    modifier: Modifier = Modifier
){
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            top = 10.dp,
            bottom = 100.dp,
            start = 10.dp,
            end = 10.dp
        ),
        modifier = modifier.fillMaxSize()
    ){
        items(machineList.size){ machine ->
            MachineCard(machine = machineList[machine])
        }
    }
}

@Composable
fun MachineCard(
    modifier: Modifier = Modifier,
    machine: Machine,
    color: Color = Color(0xFFCCCCCC)
){
    Box(
        modifier = modifier
            .padding(7.5.dp)
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(color)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = machine.name,
                fontSize = 15.sp,
                color = Color(0xFF000000)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val openDialog = mutableStateOf(false)
                Text(
                    text = "Allotted To - ${machine.workerName}",
                    fontSize = 13.sp,
                    color = Color.Black
                )
                Text(
                    text = "-->",
                    fontSize = 13.sp,
                    color = Color.Red,
                    modifier = Modifier.clickable {
                        openDialog.value = true
                    }
                )
                DialogBox(
                    machine = machine.name,
                    currentWorker = machine.workerName,
                    workerList = workerList,
                    openDialog = openDialog
                )
            }
        }
    }
}

@Composable
fun DialogBox(
    modifier: Modifier = Modifier,
    machine: String,
    currentWorker: String,
    workerList: List<String>,
    openDialog: MutableState<Boolean>
){

    var text by remember { mutableStateOf("") }
    var expandedWorkerList by remember { mutableStateOf(false)}
    var textFieldSize by remember { mutableStateOf(Size.Zero)}
    var tempWorkerList by remember { mutableStateOf(workerList)}

    DisposableEffect(key1 = text){
        tempWorkerList = if(text.isNotEmpty()) workerList.filter { text.lowercase() in it.lowercase()  }
        else workerList
        onDispose {
            //do nothing
        }
    }

    val icon = if (expandedWorkerList)
        Icons.Filled.ArrowDropUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.ArrowDropDown

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "ReAllocation Of $machine")
            },
            text = {
                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = "The ML allotted $machine to $currentWorker.Do you want to reallocate $machine to different worker? If, you don't want to proceed future dismiss this dialog",
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth())
                    OutlinedTextField(
                        value = text,
                        singleLine = true,
                        onValueChange = { text = it },
                        label = {Text (text = "New Worker Name")},
                        trailingIcon = {
                            Icon(
                                icon,
                                "contentDescription",
                                Modifier.clickable { expandedWorkerList = !expandedWorkerList },
                                tint = Color(0xFF000000)
                            )
                        },
                        modifier = Modifier.onGloballyPositioned { coordinates ->
                            //This value is used to assign to the DropDown the same width
                            textFieldSize = coordinates.size.toSize()
                        }
                    )
                    DropdownMenu(
                        expanded = expandedWorkerList,
                        onDismissRequest = { expandedWorkerList = false },
                        modifier = Modifier.width(
                                with(LocalDensity.current){
                                    textFieldSize.width.toDp()
                                })

                    ) {
                        tempWorkerList.forEach { label ->
                            DropdownMenuItem(onClick = {
                                text = label
                            }) {
                                Text(text = label)
                            }
                        }
                    }
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = { openDialog.value = false }
                    ) {
                        Text("Dismiss")
                    }
                    OutlinedButton(
                        onClick = { openDialog.value = false }
                    ) {
                        Text("ReAllot")
                    }
                }
            }
        )
    }
}

@Composable
fun WorkerLayout(
    modifier: Modifier = Modifier
) {
    val workerStatusList by remember {
        mutableStateOf(workerStatus)
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(workerStatusList) { worker ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFCCCCCC))
                        .padding(10.dp, 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = worker.name,
                        modifier = Modifier.weight(1f),
                        overflow = TextOverflow.Ellipsis,
                        color = Color(0xFF101010),
                        fontSize = 16.sp
                    )
                    LazyRow(
                        modifier = Modifier.weight(3f),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        items(worker.machineAllotted.size) { machine ->
                            val openDialog = mutableStateOf(false)
                            Text(
                                text = machine.toString(),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(Color(0xFFb29700))
                                    .aspectRatio(1f)
                                    .padding(10.dp)
                                    .clickable {
                                        openDialog.value = true
                                    }
                            )
                            DialogBox(
                                machine = machine.toString(),
                                currentWorker = worker.name,
                                workerList = workerList,
                                openDialog = openDialog
                            )

                        }
                    }
                }
            }
        }
    }
}