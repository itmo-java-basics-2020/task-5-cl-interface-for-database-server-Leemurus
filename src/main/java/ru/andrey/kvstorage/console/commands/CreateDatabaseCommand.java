package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;

public class CreateDatabaseCommand implements DatabaseCommand {
    private String tableName;
    private ExecutionEnvironment env;

    public CreateDatabaseCommand(ExecutionEnvironment env, String tableName) {
        this.env = env;
        this.tableName = tableName;
    }

    @Override
    public DatabaseCommandResult execute() {
        env.addDatabase(null);
        return DatabaseCommandResult.success("Database '" + tableName + "' was created");
    }
}
