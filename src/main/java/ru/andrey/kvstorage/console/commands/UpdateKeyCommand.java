package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class UpdateKeyCommand implements DatabaseCommand {
    private String tableName;
    private String objectKey;
    private String objectValue;
    private String databaseName;
    private ExecutionEnvironment env;

    public UpdateKeyCommand(ExecutionEnvironment env, String databaseName, String tableName, String objectKey,
                            String objectValue) {
        this.env = env;
        this.tableName = tableName;
        this.objectKey = objectKey;
        this.objectValue = objectValue;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> database = env.getDatabase(databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.error("Invalid name of database");
        }

        try {
            database.get().write(tableName, objectKey, objectValue);
        } catch (DatabaseException ex) {
            return DatabaseCommandResult.error(ex.getMessage());
        }

        return DatabaseCommandResult.success("Key was updated");
    }
}
