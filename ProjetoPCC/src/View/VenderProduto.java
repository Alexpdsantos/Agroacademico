package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.Date;

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

import com.toedter.calendar.JDateChooser;

import Controller.VenderControl;
import Model.VenderModel;

public class VenderProduto extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDescricao;
	private JTextField textQuantidade;
	private JTextField textValorKg;
	private JTextField textValorTotalKg;
	private JTextField textQualidade;
	private JTextField textDesconto;
	private JTextField textCliente;
	private JTextField textProduto;
	private JTextField textLote;
	private JLabel lblEstoque = new JLabel("");

	JDateChooser dataVenda = new JDateChooser();
	float vVenda;
	float vDesc;
	float vTotal;

	EstoqueBeneficiado eB = new EstoqueBeneficiado();

	VenderControl control = new VenderControl();
	private JTextField textTalhao;
	private JTextField textTipo;
	private JTextField textIdBeneficiado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VenderProduto frame = new VenderProduto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */

	public VenderProduto() {
		setTitle("Vender Produto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 485, 495);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblProdutoVendido = new JLabel("Venda de Produtos");
		lblProdutoVendido.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblCodigo = new JLabel("Produto");

		JLabel lblNewLabel = new JLabel("Quantidade");

		JLabel lblNewLabel_1 = new JLabel("Pre\u00E7o por Kg   R$");

		textDescricao = new JTextField();
		textDescricao.setColumns(10);

		textQuantidade = new JTextField();
		textQuantidade.setColumns(10);

		textValorKg = new JTextField();
		textValorKg.addFocusListener(new FocusAdapter() {
			@Override

			public void focusLost(FocusEvent e) {
				try {

					textValorTotalKg.setText(textValorKg.getText());

				} catch (NumberFormatException e3) {
					JOptionPane.showMessageDialog(null,
							"Valor de Venda não pode estar Vazio. Por favor, digite um valor!");
				}
			}
		});
		textValorKg.setColumns(10);

		JLabel lblValorTotalDa = new JLabel("Valor total por Kg   R$");

		textValorTotalKg = new JTextField();
		textValorTotalKg.setEditable(false);
		textValorTotalKg.setColumns(10);

		JButton btnVender = new JButton("Vender");
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float q1 = Float.parseFloat(textQuantidade.getText());
				float q2 = Float.parseFloat(lblEstoque.getText());
				if (q1 > q2) {
					JOptionPane.showMessageDialog(null, "Quantidade em estoque Insuficiente!");
				} else {

					String idBeneficiado = textIdBeneficiado.getText();
					String produto = textProduto.getText();
					String descricao = textDescricao.getText();
					String talhao = textTalhao.getText();
					String lote = textLote.getText();
					String tipo = textTipo.getText();
					String qualidade = textQualidade.getText();
					float quantidade = Float.parseFloat(textQuantidade.getText());
					double valorItem = Double.parseDouble(textValorKg.getText());
					int desconto = Integer.parseInt(textDesconto.getText());
					double valorTotal = Double.parseDouble(textValorTotalKg.getText());
					String cliente = textCliente.getText();
					Date data = (Date) dataVenda.getDate();

					try {

						if (control.insert(idBeneficiado, produto, descricao, talhao, lote, tipo, qualidade, quantidade,
								valorItem, desconto, valorTotal, cliente, data)) {
							JOptionPane.showMessageDialog(rootPane, "Venda do produto " + produto
									+ " Realizada com sucesso para o Cliente " + cliente + ".");

							// CHAMADA DO MÉTODO QUE PREENCHE A TABELA DE PRODUTOS

							// Limpar os campos após a inserção do novo produto na tabela
							textIdBeneficiado.setText("");
							textProduto.setText("");
							textTipo.setText("");
							textDescricao.setText("");
							textLote.setText("");
							textTalhao.setText("");
							textQualidade.setText("");
							textQuantidade.setText("");
							textValorKg.setText("");
							textDesconto.setText("");
							textValorTotalKg.setText("");
							textCliente.setText("");
							lblEstoque.setText("");

						} else {
							JOptionPane.showMessageDialog(rootPane, "Erro ao vender");
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				VenderProduto.this.setVisible(false);
			}
		});

		JButton btnLimparCampos = new JButton("Limpar");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblEstoque.setText("");
				textTalhao.setText("");
				textIdBeneficiado.setText("");
				textTipo.setText("");
				textProduto.setText("");
				textDescricao.setText("");
				textQuantidade.setText("");
				textValorKg.setText("");
				textValorTotalKg.setText("");
				textCliente.setText("");
				textQualidade.setText("");
				textDesconto.setText("");
				textLote.setText("");

			}
		});

		JLabel lblDataDaVenda = new JLabel("Data da Venda");

		JLabel lblDesconto = new JLabel("Desconto   %");

		textDesconto = new JTextField();
		textDesconto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					vVenda = Float.parseFloat(textValorKg.getText());
					vDesc = Float.parseFloat(textDesconto.getText());
					vTotal = (vDesc * vVenda) / 100;

					textValorTotalKg.setText(String.valueOf(vVenda - vTotal));

				} catch (NumberFormatException e5) {
					JOptionPane.showMessageDialog(null,
							"Valor de desconto não pode estar Vazio. Por favor, digite um valor!");
				}
			}
		});
		textDesconto.setColumns(10);

		JLabel lblComprador = new JLabel("Cliente");

		textCliente = new JTextField();
		textCliente.setColumns(10);

		JLabel lblQualidade = new JLabel("Qualidade");

		textQualidade = new JTextField();
		textQualidade.setColumns(10);

		textLote = new JTextField();
		textLote.setColumns(10);

		JLabel lblLote = new JLabel("Lote");

		textProduto = new JTextField();
		textProduto.setColumns(10);

		textTalhao = new JTextField();
		textTalhao.setColumns(10);

		JLabel lblTalhao = new JLabel("Talh\u00E3o");

		textTipo = new JTextField();
		textTipo.setColumns(10);

		JLabel lblTipo = new JLabel("Tipo");

		textIdBeneficiado = new JTextField();
		textIdBeneficiado.setEnabled(false);
		textIdBeneficiado.setEditable(false);
		textIdBeneficiado.setColumns(10);

		JLabel lblQuantidadeEmEstoque = new JLabel("Estoque:");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup().addGap(25)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(lblCodigo)
								.addComponent(lblTalhao).addComponent(lblLote).addComponent(lblTipo)
								.addComponent(lblQualidade).addComponent(lblNewLabel).addComponent(lblNewLabel_1)
								.addComponent(lblDesconto).addComponent(lblValorTotalDa).addComponent(lblComprador)
								.addComponent(lblDataDaVenda).addComponent(btnVender)))
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
								.addComponent(lblQuantidadeEmEstoque).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblEstoque)))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnLimparCampos).addGap(18)
								.addComponent(btnCancelar))
						.addComponent(dataVenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(18).addComponent(lblProdutoVendido))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(textProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(textDescricao,
										GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textCliente, Alignment.LEADING)
										.addComponent(textValorTotalKg, Alignment.LEADING)
										.addComponent(textDesconto, Alignment.LEADING)
										.addComponent(textValorKg, Alignment.LEADING)
										.addComponent(textTalhao, Alignment.LEADING)
										.addComponent(textQualidade, Alignment.LEADING)
										.addComponent(textTipo, Alignment.LEADING)
										.addComponent(textLote, Alignment.LEADING).addComponent(textQuantidade,
												Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
								.addGap(18).addComponent(textIdBeneficiado, GroupLayout.PREFERRED_SIZE, 28,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(107, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblProdutoVendido)
								.addComponent(lblQuantidadeEmEstoque).addComponent(lblEstoque))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCodigo).addComponent(textDescricao, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textTalhao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTalhao).addComponent(textIdBeneficiado, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textLote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLote))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTipo))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textQualidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQualidade))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textValorKg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textDesconto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDesconto))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textValorTotalKg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblValorTotalDa))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblComprador))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(dataVenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDataDaVenda))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnLimparCampos)
								.addComponent(btnVender).addComponent(btnCancelar))
						.addContainerGap(19, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

	public void pegarValores(VenderModel model) {
		textIdBeneficiado.setText(model.getIdBeneficiado());
		textProduto.setText(model.getProduto());
		textDescricao.setText(model.getDescricao());
		textLote.setText(model.getLote());
		textTipo.setText(model.getTipo());
		textTalhao.setText(model.getTalhao());
		textQualidade.setText(model.getQualidade());
		textQuantidade.setText("0.0");
		textValorKg.setText("0.0");
		textDesconto.setText("0");
		textValorTotalKg.setText("0.0");
		textCliente.setText("-");
		lblEstoque.setText(model.getEstoque());

	}
}
