package com.logreapermq.LogReaperMQ.Security;

public enum SubTopicTypes {
    INFO_T("INFO"),
    FATAL_T("FATAL"),
    ERROR_T("ERROR"),
    WARN_T("WARN"),
    DEBUG_T("DEBUG"),
    TRACE_T("TRACE");

    private final String subtopicType;

    private SubTopicTypes(final String subtopicType) {
        this.subtopicType = subtopicType;
    }

    public String getSubtopicType() {
        return this.subtopicType;
    }
}
