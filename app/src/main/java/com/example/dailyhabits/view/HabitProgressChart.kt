package com.example.dailyhabits.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dailyhabits.viewmodel.HabitViewModel

@Composable
fun HabitProgressChart(viewModel: HabitViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "📊 Fortschritt Übersicht",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }

        if (viewModel.habits.isEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Keine Gewohnheiten vorhanden",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        } else {
            items(viewModel.habits) { habit ->
                HabitProgressCard(habit = habit)
            }

            item {
                SummaryCard(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun HabitProgressCard(habit: com.example.dailyhabits.model.Habit) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = habit.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )

                if (habit.isCompletedToday) {
                    Text(
                        text = "✅",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Streak: ${habit.streakCount} Tage",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "${(habit.streakCount * 100 / 30).coerceAtMost(100)}%",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Progress Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(
                            fraction = (habit.streakCount.coerceAtMost(30) / 30f)
                        )
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            when {
                                habit.streakCount >= 21 -> MaterialTheme.colorScheme.primary
                                habit.streakCount >= 14 -> MaterialTheme.colorScheme.tertiary
                                habit.streakCount >= 7 -> MaterialTheme.colorScheme.secondary
                                else -> MaterialTheme.colorScheme.outline
                            }
                        )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = when {
                    habit.streakCount >= 21 -> "🏆 Gewohnheit etabliert!"
                    habit.streakCount >= 14 -> "💪 Gut dabei!"
                    habit.streakCount >= 7 -> "🌱 Am Wachsen"
                    habit.streakCount > 0 -> "🚀 Gestartet"
                    else -> "⭐ Bereit zum Start"
                },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun SummaryCard(viewModel: HabitViewModel) {
    val totalHabits = viewModel.habits.size
    val completedToday = viewModel.habits.count { it.isCompletedToday }
    val averageStreak = if (totalHabits > 0)
        viewModel.habits.sumOf { it.streakCount } / totalHabits
    else 0

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "📈 Zusammenfassung",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                StatItem(
                    label = "Heute",
                    value = "$completedToday/$totalHabits",
                    icon = "✅"
                )

                StatItem(
                    label = "Durchschnitt",
                    value = "$averageStreak Tage",
                    icon = "📊"
                )

                StatItem(
                    label = "Gesamt",
                    value = "$totalHabits",
                    icon = "🎯"
                )
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String, icon: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = icon,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}