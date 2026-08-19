import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {
    private Deque<String> undoStack = new ArrayDeque<>();
    private Deque<String> redoStack = new ArrayDeque<>();

    public void addOperation(String operation) {
        if (operation == null || operation.trim().isEmpty()) {
            return;
        }
        undoStack.push(operation);
        redoStack.clear();
        printState("Add: " + operation);
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Undo failed: Stack is empty.");
            return;
        }
        String op = undoStack.pop();
        redoStack.push(op);
        printState("Undo: " + op);
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Redo failed: Stack is empty.");
            return;
        }
        String op = redoStack.pop();
        undoStack.push(op);
        printState("Redo: " + op);
    }

    private void printState(String action) {
        System.out.println(action);
        System.out.println("Undo Stack: " + undoStack);
        System.out.println("Redo Stack: " + redoStack);
        System.out.println("-------------------------");
    }

    public static void main(String[] args) {
        TextEditorHistory editor = new TextEditorHistory();
        editor.addOperation("Type 'Hello'");
        editor.addOperation("Type ' World'");
        editor.undo();
        editor.undo();
        editor.undo();
        editor.redo();
        editor.addOperation("Type ' Java'");
        editor.redo();
    }
}