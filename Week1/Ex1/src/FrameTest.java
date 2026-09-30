import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Toolkit;

public class FrameTest {
	private JFrame frame;
	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

	public FrameTest() {
		frame = new JFrame("Frame");
		
		// para que o botao de fechar a janela termine a aplicacao
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		addFrameContent();
		
		// para que a janela se redimensione de forma a ter todo o seu conteudo visivel
		frame.pack();
	}

	public void open() {
		// para abrir a janela (torna-la visivel)
		frame.setVisible(true);
	}

	private void addFrameContent() {
		/* para organizar o conteudo em grelha (linhas x colunas)
		se um dos valores for zero, o numero de linhas ou colunas (respetivamente) fica indefinido,
		e estas sao acrescentadas automaticamente */
		frame.setLayout(new GridLayout(4,2));
		JLabel titleLabel = new JLabel("title");
		frame.add(titleLabel);
		JTextField titleText = new JTextField("title");
		frame.add(titleText);
		JLabel widthLabel = new JLabel("width");
		frame.add(widthLabel);
		JTextField widthText = new JTextField("width");
		frame.add(widthText);
		JLabel heightLabel = new JLabel("height");
		frame.add(heightLabel);
		JTextField heightText = new JTextField("height");
		frame.add(heightText);
		JButton button = new JButton("update");
		JCheckBox checkBox = new JCheckBox("center");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBox.isSelected())
					frame.setLocation((dimension.width - frame.getWidth()) / 2, (dimension.height - frame.getHeight()) / 2);
				if (widthText.getText().isEmpty() || heightText.getText().isEmpty() || titleText.getText().isEmpty() || !widthText.getText().matches("\\d+") || !heightText.getText().matches("\\d+")) {
					JOptionPane.showMessageDialog(frame, "All fields must be filled in.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String title = titleText.getText();
				int width = Integer.parseInt(widthText.getText());
				int height = Integer.parseInt(heightText.getText());
				frame.setTitle(title);
				frame.setSize(width, height);
			}
		});
		frame.add(button);
		frame.add(checkBox);
		
	}

	public static void main(String[] args) {
		FrameTest window = new FrameTest();
		window.open();
	}
}
