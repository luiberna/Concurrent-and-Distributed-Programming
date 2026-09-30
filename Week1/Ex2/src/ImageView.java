import java.awt.BorderLayout;
import java.io.File;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class ImageView {
    private final File[] images;
    private int currentIndex = 0;

    private final JFrame frame;
    private final JLabel imageDisplayLabel;
    private final JLabel imageNameLabel;

    public ImageView(File imageDirectory) {
        images = imageDirectory.listFiles((directory, name) -> {
            String lowerName = name.toLowerCase();
            return lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg") || lowerName.endsWith(".png") || lowerName.endsWith(".gif");
        });

        if (images == null || images.length == 0) {
            throw new IllegalArgumentException("The directory does not contain any supported images.");
        }

        Arrays.sort(images);

        frame = new JFrame("Image Viewer");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JButton backButton = new JButton("<<");
        backButton.addActionListener(event -> showPreviousImage());

        JButton forwardButton = new JButton(">>");
        forwardButton.addActionListener(event -> showNextImage());

        imageNameLabel = new JLabel();
        imageNameLabel.setHorizontalAlignment(JLabel.CENTER);

        imageDisplayLabel = new JLabel();
        imageDisplayLabel.setHorizontalAlignment(JLabel.CENTER);
        imageDisplayLabel.setVerticalAlignment(JLabel.CENTER);

        frame.add(backButton, BorderLayout.WEST);
        frame.add(forwardButton, BorderLayout.EAST);
        frame.add(imageNameLabel, BorderLayout.NORTH);
        frame.add(imageDisplayLabel, BorderLayout.CENTER);

        showCurrentImage();

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void showCurrentImage() {
        File imageFile = images[currentIndex];

        imageDisplayLabel.setIcon(new ImageIcon(imageFile.getAbsolutePath()));
        imageNameLabel.setText(imageFile.getName());
        frame.pack();
    }

    private void showPreviousImage() {
        currentIndex--;
        if (currentIndex < 0)
            currentIndex = images.length - 1;
        showCurrentImage();
    }

    private void showNextImage() {
        currentIndex++;
        if (currentIndex >= images.length)
            currentIndex = 0;
        showCurrentImage();
    }

    public void open() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            JOptionPane.showMessageDialog(
                    null,
                    "Please provide the image directory path.");
            System.exit(1);
        }

        File imageDirectory = new File(args[0]);

        if (!imageDirectory.isDirectory()) {
            JOptionPane.showMessageDialog(
                    null,
                    "The path is not a valid directory.");
            System.exit(1);
        }

        try {
            ImageView imageView = new ImageView(imageDirectory);
            imageView.open();
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(
                    null,
                    exception.getMessage());
        }
    }
}