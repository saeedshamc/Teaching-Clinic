// TaskSystem.java — پاسخ پروژهٔ جلسه ۲۲
public class TaskSystem {

    enum TaskStatus {
        TODO("در انتظار", 0),
        IN_PROGRESS("در حال انجام", 1),
        REVIEW("در حال بررسی", 2),
        DONE("تمام شده", 3);

        private final String label;
        private final int order;
        TaskStatus(String label, int order) { this.label = label; this.order = order; }
        public String getLabel() { return label; }
    }

    enum Priority {
        LOW(1), MEDIUM(2), HIGH(3), URGENT(4);
        private final int level;
        Priority(int level) { this.level = level; }
        public int getLevel() { return level; }
    }

    static class Task {
        String title;
        TaskStatus status;
        Priority priority;

        Task(String title, TaskStatus status, Priority priority) {
            this.title = title;
            this.status = status;
            this.priority = priority;
        }

        void advance() {
            TaskStatus[] values = TaskStatus.values();
            int next = status.ordinal() + 1;
            if (next < values.length) {
                status = values[next];
            }
        }

        void print() {
            System.out.printf("Task: %s — %s (%s) [%s]%n",
                    title, status.getLabel(), status.name(), priority.name());
        }
    }

    public static void main(String[] args) {
        Task t1 = new Task("Fix bug", TaskStatus.TODO, Priority.HIGH);
        Task t2 = new Task("Write docs", TaskStatus.IN_PROGRESS, Priority.LOW);

        t1.print();
        t1.advance();
        t1.print();
        t1.advance();
        t1.print();

        t2.print();
        t2.advance();
        t2.print();
    }
}
