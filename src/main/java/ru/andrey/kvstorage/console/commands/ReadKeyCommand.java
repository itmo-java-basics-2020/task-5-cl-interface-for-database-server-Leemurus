package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKeyCommand implements DatabaseCommand {
    private String tableName;
    private String objectKey;
    private String databaseName;
    private ExecutionEnvironment env;

    public ReadKeyCommand(ExecutionEnvironment env, String databaseName, String tableName, String objectKey) {
        this.env = env;
        this.tableName = tableName;
        this.objectKey = objectKey;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> database = env.getDatabase(databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.error("Invalid name of database");
        }

        try {
            String result = database.get().read(tableName, objectKey);
            return DatabaseCommandResult.success("Value = " + result);
        } catch (DatabaseException ex) {
            return DatabaseCommandResult.error(ex.getMessage());
        }
    }
}
