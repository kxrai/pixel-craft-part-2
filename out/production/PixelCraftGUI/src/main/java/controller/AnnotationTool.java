package controller;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class AnnotationTool {

    private Canvas canvas;
    private GraphicsContext gc;

    public AnnotationTool(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        initCanvas();
    }

    private void initCanvas() {
        gc.setStroke(Color.BLACK); // Default color
        gc.setLineWidth(2); // Default line width

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this::onMousePressed);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::onMouseDragged);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, this::onMouseReleased);
    }

    private void onMousePressed(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            gc.beginPath();
            gc.moveTo(e.getX(), e.getY());
            gc.stroke();
        }
    }

    private void onMouseDragged(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        }
    }

    private void onMouseReleased(MouseEvent e) {
        gc.closePath();
    }

    public void setColor(Color color) {
        gc.setStroke(color);
    }

    public void setLineWidth(double width) {
        gc.setLineWidth(width);
    }

    public void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
