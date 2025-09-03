package com.correxapp.presentation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.correxapp.R
import com.correxapp.domain.model.ClassWithStudents
import com.correxapp.presentation.ui.viewmodel.ClassesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassesScreen(
    navController: NavController,
    viewModel: ClassesViewModel = hiltViewModel()
) {
    val classes by viewModel.classes.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.my_classes)) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_class))
            }
        }
    ) { paddingValues ->
        // Se a lista de turmas estiver vazia, mostramos uma mensagem útil.
        if (classes.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(R.string.no_classes_created_yet))
            }
        } else {
            // Se houver turmas, mostramos em uma lista otimizada (LazyColumn).
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(classes) { classWithStudents ->
                    ClassCard(
                        classWithStudents = classWithStudents,
                        onClick = {
                            // Futuramente, navegará para a tela de detalhes da turma
                            // Deixamos este comentário como um lembrete para a nossa próxima missão!
                        }
                    )
                }
            }
        }

        // Este bloco controla a exibição do diálogo para adicionar turma.
        if (showDialog) {
            AddClassDialog(
                onDismiss = { showDialog = false },
                onConfirm = { name, subject ->
                    viewModel.addClass(name, subject)
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun ClassCard(classWithStudents: ClassWithStudents, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = classWithStudents.classInfo.name, style = MaterialTheme.typography.titleMedium)
            classWithStudents.classInfo.subject?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = it, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.students_count, classWithStudents.students.size),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun AddClassDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.add_class)) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(stringResource(R.string.class_name_required)) }
                )
                OutlinedTextField(
                    value = subject,
                    onValueChange = { subject = it },
                    label = { Text(stringResource(R.string.subject_optional)) }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(name, subject) },
                enabled = name.isNotBlank() // O botão só fica ativo se o nome for preenchido
            ) {
                Text(stringResource(R.string.add))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}