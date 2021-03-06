package org.hjjang.junitexample;

public class Study {
//    private StudyStatus status = StudyStatus.DRAFT;
    private StudyStatus status;

    private int limit;

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit 는 0보다 커야한다.");
        }
        this.limit = limit;
    }

    public StudyStatus getStatus(){
        return this.status;
    }

    public int getLimit() {
        return limit;
    }
}
