package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

import Controller.CadastrarProdutoControl;
import DAO.CadastrarProdutoDAO;


public class CadastrarProduto extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CadastrarProdutoControl control = new CadastrarProdutoControl();
	CadastrarProdutoDAO listar = new CadastrarProdutoDAO();
	
	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textDescricao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarProduto frame = new CadastrarProduto();
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
	public CadastrarProduto() {

		setTitle("Cadastrar Produto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 347, 290);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblLotes = new JLabel("Codigo do Produto");
		
		textCodigo = new JTextField();
		textCodigo.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int codigo  = Integer.parseInt(textCodigo.getText());
				String descricao = textDescricao.getText();
				
				
				try{
					if(control.insert(codigo, descricao)){
					JOptionPane.showMessageDialog(rootPane,"O código "+ codigo+" foi cadastrado com sucesso para o produto "+ descricao+".");
					
					}
					
					textCodigo.setText("");
					textDescricao.setText("");
					
					}catch(SQLException e) {
						JOptionPane.showMessageDialog(rootPane,"Já existe um Produto com o código "+ codigo+"! Por favor, Digite outro Código...");
						
					}catch (HeadlessException e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(rootPane,"Erro na Persistência dos dados!");
					}

		}});
				
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastrarProduto.this.setVisible(false);
			}
		});
		
		JLabel lblCadastroTalho = new JLabel("Cadastro de Produto");
		lblCadastroTalho.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JButton btnNewButton = new JButton("Limpar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textCodigo.setText("");
				textDescricao.setText("");
		
				
				
			}
		});
		
		textDescricao = new JTextField();
		textDescricao.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Descri\u00E7\u00E3o");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel)
								.addComponent(btnCadastrar)
								.addComponent(lblLotes))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnNewButton)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnCancelar))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(textDescricao, Alignment.LEADING)
									.addComponent(textCodigo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
							.addGap(42))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCadastroTalho)
							.addGap(89))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCadastroTalho)
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLotes))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrar)
						.addComponent(btnNewButton)
						.addComponent(btnCancelar))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
