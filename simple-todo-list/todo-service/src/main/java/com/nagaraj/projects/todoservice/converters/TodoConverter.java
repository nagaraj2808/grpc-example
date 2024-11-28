package com.nagaraj.projects.todoservice.converters;

import com.nagaraj.projects.proto.todoservice.Todo;

public class TodoConverter {
    public static Todo fromEntityToProto(com.nagaraj.projects.todoservice.domains.Todo entity) {
        return Todo.newBuilder()
                .setTodoId(entity.getTodoId())
                .setTitle(entity.getTitle())
                .setDone(entity.getDone())
                .setCreatedTime(DateConverter.toTimestamp(entity.getCreationTime()))
                .setUpdatedTime(DateConverter.toTimestamp(entity.getLastUpdated()))
                .build();
    }
}
