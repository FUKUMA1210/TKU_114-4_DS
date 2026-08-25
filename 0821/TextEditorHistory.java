import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {

    static void addOperation(Deque<String> undoStack,
                             Deque<String> redoStack,
                             String operation) {

        undoStack.push(operation);
        redoStack.clear();

        System.out.println("新增：" + operation);
        printState(undoStack, redoStack);
    }

    static void undo(Deque<String> undoStack,
                     Deque<String> redoStack) {

        String operation = undoStack.pollFirst();

        if (operation == null) {
            System.out.println("Undo：沒有可以復原的操作");
        } else {
            redoStack.push(operation);
            System.out.println("Undo：" + operation);
        }

        printState(undoStack, redoStack);
    }

    static void redo(Deque<String> undoStack,
                     Deque<String> redoStack) {

        String operation = redoStack.pollFirst();

        if (operation == null) {
            System.out.println("Redo：沒有可以重做的操作");
        } else {
            undoStack.push(operation);
            System.out.println("Redo：" + operation);
        }

        printState(undoStack, redoStack);
    }

    static void printState(Deque<String> undoStack,
                           Deque<String> redoStack) {

        System.out.println("Undo Stack：" + undoStack);
        System.out.println("Redo Stack：" + redoStack);
        System.out.println();
    }

    public static void main(String[] args) {

        Deque<String> undoStack = new ArrayDeque<>();
        Deque<String> redoStack = new ArrayDeque<>();

        System.out.println("=== 文字編輯 Undo/Redo ===");

        addOperation(undoStack, redoStack, "輸入 Hello");
        addOperation(undoStack, redoStack, "輸入 Java");
        addOperation(undoStack, redoStack, "刪除 Java");

        undo(undoStack, redoStack);
        undo(undoStack, redoStack);

        redo(undoStack, redoStack);

        addOperation(undoStack, redoStack, "輸入 World");

        redo(undoStack, redoStack);

        undo(undoStack, redoStack);
        undo(undoStack, redoStack);
        undo(undoStack, redoStack);
        undo(undoStack, redoStack);
    }
}