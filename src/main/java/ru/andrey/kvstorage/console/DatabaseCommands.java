package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.console.commands.CreateDatabaseCommand;
import ru.andrey.kvstorage.console.commands.CreateTableCommand;
import ru.andrey.kvstorage.console.commands.ReadKeyCommand;
import ru.andrey.kvstorage.console.commands.UpdateKeyCommand;

public enum DatabaseCommands {
    CREATE_DATABASE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) throws IllegalArgumentException {
            if (args.length != 1) {
                throw new IllegalArgumentException("Incorrect number of arguments");
            }
            return new CreateDatabaseCommand(env, args[DATABASE_NAME]);
        }
    },
    CREATE_TABLE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) throws IllegalArgumentException {
            if (args.length != 2) {
                throw new IllegalArgumentException("Incorrect number of arguments");
            }
            return new CreateTableCommand(env, args[DATABASE_NAME], args[TABLE_NAME]);
        }
    },
    UPDATE_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) throws IllegalArgumentException {
            if (args.length != 4) {
                throw new IllegalArgumentException("Incorrect number of arguments");
            }
            return new UpdateKeyCommand(env, args[DATABASE_NAME], args[TABLE_NAME], args[KEY], args[VALUE]);
        }
    },
    READ_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) throws IllegalArgumentException {
            if (args.length != 3) {
                throw new IllegalArgumentException("Incorrect number of arguments");
            }
            return new ReadKeyCommand(env, args[DATABASE_NAME], args[TABLE_NAME], args[KEY]);
        }
    };

    abstract public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
            throws IllegalArgumentException;

    private static final int DATABASE_NAME = 0;
    private static final int TABLE_NAME = 1;
    private static final int KEY = 2;
    private static final int VALUE = 3;
}
