# clean_code_tp2_axel  
# API Todo – TP Clean Architecture (ISEP)

Ce projet implémente une API REST de gestion de tâches en Java avec SparkJava, selon l’architecture hexagonale (Clean Architecture).

## Structure rapide

- `presentation/` : API REST (`TodoController`)
- `application/` : logique métier (`TodoManager`) + port `ITodoRepository`
- `persistence/inmemory/` : persistance en mémoire
- `persistence/csvfiles/` : persistance en fichier CSV

## Fichier CSV

Les données sont stockées dans :

%APPDATA%\isep-todo-api\todos.csv

## Tester l’API

Lance l’application avec `Main.java`, puis utilise les commandes suivantes dans l’invite de commande :

### Ajouter une tâche

```bash
curl -X POST http://localhost:4567/todos ^
  -H "Content-Type: application/json" ^
  -d "{\"name\": \"Réviser\", \"dueDate\": \"2025-05-25\"}"
```

### Lister toutes les tâches

```bash
curl http://localhost:4567/todos
```
