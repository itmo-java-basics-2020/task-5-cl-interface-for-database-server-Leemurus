package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    Optional<String> getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    static DatabaseCommandResultImpl success(String result) {
        return new DatabaseCommandResultImpl(DatabaseCommandStatus.SUCCESS, result);
    }

    static DatabaseCommandResultImpl error(String result) {
        return new DatabaseCommandResultImpl(DatabaseCommandStatus.FAILED, result);
    }

    class DatabaseCommandResultImpl implements DatabaseCommandResult {
        private String result;
        private DatabaseCommandStatus status;

        private DatabaseCommandResultImpl(DatabaseCommandStatus status, String result) {
            this.status = status;
            this.result = result;
        }

        @Override
        public Optional<String> getResult() {
            return Optional.of(result);
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public boolean isSuccess() {
            return status.equals(DatabaseCommandStatus.SUCCESS);
        }

        @Override
        public Optional<String> getErrorMessage() {
            if (status == DatabaseCommandStatus.FAILED) {
                return Optional.of(result);
            }
            return Optional.empty();
        }
    }
}