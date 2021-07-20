package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import Controller.CadastrarTalhaoControl;

public class CadastrarTalhao extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CadastrarTalhaoControl control = new CadastrarTalhaoControl();

	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textVariedade;
	private JTextField textArea;
	private JTextField textQuantPes;
	private JTextField textEspacamento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarTalhao frame = new CadastrarTalhao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastrarTalhao() {
		setTitle("Cadastrar Talh\u00E3o");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 379, 346);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblLotes = new JLabel("Nome");

		textCodigo = new JTextField();
		textCodigo.setColumns(10);

		JLabel lblVariedade = new JLabel("Variedade");

		textVariedade = new JTextField();
		textVariedade.setColumns(10);

		JLabel lblAre = new JLabel("\u00C1rea em Hectares");

		textArea = new JTextField();
		textArea.setColumns(10);

		JLabel lblQuantidadeDePs = new JLabel("Quantidade de P\u00E9s");

		textQuantPes = new JTextField();
		textQuantPes.setColumns(10);

		JLabel lblEspacamento = new JLabel("Espa\u00E7amento");

		textEspacamento = new JTextField();
		textEspacamento.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String nome = textCodigo.getText();
				int quantPes = Integer.parseInt(textQuantPes.getText());
				String espacamento = textEspacamento.getText();
				String area = textArea.getText();
				String variedade = textVariedade.getText();

				try {
					if (control.insert(nome, quantPes, espacamento, area, variedade)) {
						JOptionPane.showMessageDialog(rootPane,
								"O talhão " + variedade + " foi cadastrado com sucesso.");
					}
					TelaInicial inicial = new TelaInicial();
					inicial.preencheComboTalhao();

					textCodigo.setText("");
					textVariedade.setText("");
					textArea.setText("");
					textQuantPes.setText("");
					textEspacamento.setText("");

				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(rootPane,
							" Já existe o Talhão " + variedade +". Por favor, digite outro Nome...");
				} catch (HeadlessException e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(rootPane, "Erro na Persistência dos dados!");
				}

			}
		});
		;

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastrarTalhao.this.setVisible(false);
			}
		});

		JLabel lblCadastroTalho = new JLabel("Cadastro de  Talh\u00E3o");
		lblCadastroTalho.setFont(new Font("Tahoma", Font.BOLD, 17));

		JButton btnNewButton = new JButton("Limpar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textCodigo.setText("");
				textVariedade.setText("");
				textArea.setText("");
				textQuantPes.setText("");
				textEspacamento.setText("");

			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(91).addComponent(lblCadastroTalho))
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addGroup(
								gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(btnCadastrar)
										.addComponent(lblEspacamento).addComponent(lblQuantidadeDePs)
										.addComponent(lblAre).addComponent(lblVariedade).addComponent(lblLotes))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addComponent(textQuantPes).addComponent(textEspacamento)
														.addComponent(textVariedade).addComponent(textArea)
														.addComponent(textCodigo)))
										.addGroup(gl_contentPane.createSequentialGroup().addGap(18)
												.addComponent(btnNewButton).addGap(18).addComponent(btnCancelar)))))
				.addContainerGap(78, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap().addComponent(lblCadastroTalho).addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblLotes).addComponent(
						textCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane
						.createParallelGroup(Alignment.BASELINE).addComponent(lblVariedade).addComponent(textVariedade,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblAre).addComponent(
						textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblQuantidadeDePs)
						.addComponent(textQuantPes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblEspacamento)
						.addComponent(textEspacamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnCadastrar)
						.addComponent(btnNewButton).addComponent(btnCancelar))
				.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}

}
