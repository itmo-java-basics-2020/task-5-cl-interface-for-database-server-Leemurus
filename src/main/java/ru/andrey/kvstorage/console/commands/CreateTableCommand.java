package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CreateTableCommand implements DatabaseCommand {
    private String tableName;
    private String databaseName;
    private ExecutionEnvironment env;

    public CreateTableCommand(ExecutionEnvironment env, String databaseName, String tableName) {
        this.env = env;
        this.tableName = tableName;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() {
        // This part of code is duplicate, because I can't add method validateTableName in interface -
        // it isn't logical. I guess, that ExecutionEnvironment.getDatabase method should throw DatabaseException,
        // if it can't give me table
        Optional<Database> database = env.getDatabase(databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.error("Invalid name of database");
        }

        try {
            database.get().createTableIfNotExists(tableName);
        } catch (DatabaseException ex) {
            return DatabaseCommandResult.error(ex.getMessage());
        }

        return DatabaseCommandResult.success("Table '" + tableName + "' was created");
    }
}
