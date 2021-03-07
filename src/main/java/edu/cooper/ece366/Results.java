package edu.cooper.ece366;

class Result<T> {

    private final T t;
    private final Status status;

    public T get() {
        if (t == null) {
            throw new IllegalArgumentException("no value set");
        }

        return t;
    }

    public Status getStatus() {
        return status;
    }

    enum Status {
        GOOD,
        BAD,
        UGLY
    }

    private Result(T t, Status status) {
        this.t = t;
        this.status = status;
    }

    public static <T> Result<T> of(T t) {
        return new Result<>(t, Status.GOOD);
    }

    public static <T> Result<T> error(Status status) {
        return new Result<>(null, status);
    }
}