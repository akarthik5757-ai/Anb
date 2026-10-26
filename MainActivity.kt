package com.anbutravels.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val TravelBlue = Color(0xFF123A70)
private val TravelOrange = Color(0xFFFF9800)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                AnbuTravelsApp()
            }
        }
    }
}

@Composable
fun AnbuTravelsApp() {
    val context = LocalContext.current
    var selectedTab by remember { mutableStateOf("Home") }

    val destinations = listOf(
        "Chennai" to "City tours and business trips",
        "Madurai" to "Temples and sightseeing",
        "Rameswaram" to "Pilgrimage and family tours",
        "Kodaikanal" to "Hills and nature trips",
        "Ooty" to "Mountain and holiday tours",
        "Kanyakumari" to "Coastal sightseeing"
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                listOf("Home", "Trips", "Contact").forEach { tab ->
                    NavigationBarItem(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        icon = {
                            Text(
                                when (tab) {
                                    "Home" -> "⌂"
                                    "Trips" -> "✈"
                                    else -> "☎"
                                },
                                fontSize = 20.sp
                            )
                        },
                        label = { Text(tab) }
                    )
                }
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F7FB))
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            TravelBlue,
                            RoundedCornerShape(22.dp)
                        )
                        .padding(22.dp)
                ) {
                    Text(
                        "ANBU TRAVELS",
                        color = TravelOrange,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        "Your Journey,\nOur Responsibility!",
                        color = Color.White,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        "Safe journeys • Comfortable rides • Happy travels",
                        color = Color.White
                    )

                    Spacer(Modifier.height(18.dp))

                    Button(
                        onClick = {
                            selectedTab = "Trips"
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TravelOrange
                        )
                    ) {
                        Text("Explore Trips", color = Color.White)
                    }
                }
            }

            item {
                Text(
                    when (selectedTab) {
                        "Home" -> "Welcome to Anbu Travels"
                        "Trips" -> "Explore Our Destinations"
                        else -> "Contact Anbu Travels"
                    },
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TravelBlue
                )
            }

            if (selectedTab == "Contact") {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                "Plan your next journey",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text("Call us to discuss your travel requirements.")

                            Button(
                                onClick = {
                                    val intent = Intent(
                                        Intent.ACTION_DIAL,
                                        Uri.parse("tel:")
                                    )
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Call Anbu Travels")
                            }

                            OutlinedButton(
                                onClick = {
                                    val intent = Intent(
                                        Intent.ACTION_SENDTO,
                                        Uri.parse("smsto:")
                                    )
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Send SMS")
                            }

                            Text(
                                "Add your real business phone number to enable direct contact."
                            )
                        }
                    }
                }
            } else {
                items(destinations.size) { index ->
                    val (city, description) = destinations[index]

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 2.dp
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    city,
                                    fontSize = 19.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TravelBlue
                                )

                                Spacer(Modifier.height(5.dp))

                                Text(
                                    description,
                                    color = Color.DarkGray
                                )
                            }

                            Button(
                                onClick = {
                                    val message = Uri.encode(
                                        "Hello Anbu Travels! " +
                                        "I want to book a trip to $city."
                                    )
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(
                                            "https://wa.me/?text=$message"
                                        )
                                    )
                                    context.startActivity(intent)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = TravelBlue
                                )
                            ) {
                                Text("Book")
                            }
                        }
                    }
                }
            }

            item {
                Spacer(Modifier.height(10.dp))

                Text(
                    "Thank you for choosing Anbu Travels!",
                    modifier = Modifier.fillMaxWidth(),
                    color = TravelBlue,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    "Reliable travel for every journey.",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
