package org.ditto.lib.dbroom.misc;

public enum CommandStatus {
    NEW("command was newly created locally"),
		PROCESSING("command was processing on server"),
		OK("command was processed sucessfully on server without any error"),
		FAILED("command was processed failed on server with errors");

		private String description;

		CommandStatus(String description) {
			this.description = description;
		}
	}
