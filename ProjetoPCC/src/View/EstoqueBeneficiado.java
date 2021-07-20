package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import Controller.BeneficiadoControl;
import DAO.BeneficiadoDAO;
import DAO.CadastrarProdutoDAO;
import DAO.CadastrarTalhaoDAO;
import Model.BeneficiadoModel;
import Model.CadastrarProdutoModel;
import Model.CadastrarTalhaoModel;
import Model.VenderModel;
import java.awt.event.WindowFocusListener;

public class EstoqueBeneficiado extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BeneficiadoControl control = new BeneficiadoControl();
	BeneficiadoDAO bDAO = new BeneficiadoDAO();

	private JTable table;
	private JTextField textLote;
	private JTextField textQuantidade;
	private JTextField textQualidade;
	private JTextField textDescricao;
	private JTextField textFiltraProduto;
	private JTextField textFiltraLote;
	private JTextField textUmidade;
	private JTextField textFiltraUmidade;
	private JTextField textFieldTotalRendimento;
	private JTextField textRendimento;
	JDateChooser datac = new JDateChooser();
	JDateChooser dataInicial = new JDateChooser();
	JDateChooser dataFim = new JDateChooser();
	private JComboBox<Object> CBTipo = new JComboBox<Object>();
	private JComboBox<Object> CBProduto = new JComboBox<Object>();
	private JComboBox<Object> CBTalhao = new JComboBox<Object>();
	private JCheckBox cbxProduto = new JCheckBox("Produto");

	private JCheckBox cbxLote = new JCheckBox("Lote");

	private JCheckBox cbxUmidade = new JCheckBox("Umidade");

	CadastrarTalhaoDAO ctDAO = new CadastrarTalhaoDAO();
	CadastrarProdutoDAO cpDAO = new CadastrarProdutoDAO();

	int selected;
	int selectedRow;
	private JTextField textIdBeneficiado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstoqueBeneficiado frame = new EstoqueBeneficiado();
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
	public EstoqueBeneficiado() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
			
				atualizar();
				try {
					bDAO.deleteZero();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {

			}
		});
		setTitle("Estoque de Produtos Beneficiados");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(0, 0, 1051, 692);
		setModal(true);
		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, null, null, null));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, null, null, null));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1015, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(53, Short.MAX_VALUE)));

		textFiltraProduto = new JTextField();
		textFiltraProduto.setColumns(10);

		textFiltraLote = new JTextField();
		textFiltraLote.setColumns(10);

		textFiltraUmidade = new JTextField();
		textFiltraUmidade.setColumns(10);

		dataInicial.setDateFormatString("MMMM dd, yyyy");

		dataFim.setDateFormatString("MMMM dd, yyyy");

		JLabel lblInicial = new JLabel("Inicial");

		JLabel lblFinal = new JLabel("Final");

		JCheckBox cbxData = new JCheckBox("Data");
		cbxData.setEnabled(false);
		cbxData.setSelected(true);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbxData.isSelected() && cbxProduto.isSelected() && cbxLote.isSelected()
						&& cbxUmidade.isSelected()) {
					if (textFiltraProduto.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe o produto corretamente!");
					} else {
						FiltrarDataProdLoteUmidade(dataInicial.getDate(), dataFim.getDate(),
								Integer.parseInt(textFiltraProduto.getText()), textFiltraLote.getText(),
								Float.parseFloat(textFiltraUmidade.getText()));
						quantidadeTotal();
					}
				} else {

					if (cbxData.isSelected() && cbxProduto.isSelected() && cbxLote.isSelected()) {
						if (textFiltraProduto.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Informe o produto corretamente!");
						} else {
							FiltrarDataProdLote(dataInicial.getDate(), dataFim.getDate(),
									Integer.parseInt(textFiltraProduto.getText()), textFiltraLote.getText());
							quantidadeTotal();
						}
					} else {
						if (cbxData.isSelected() && cbxUmidade.isSelected() && cbxLote.isSelected()) {
							FiltrarDataLoteUmidade(dataInicial.getDate(), dataFim.getDate(),
									Float.parseFloat(textFiltraUmidade.getText()), textFiltraLote.getText());
							quantidadeTotal();
						} else {
							if (cbxData.isSelected() && cbxProduto.isSelected() && cbxUmidade.isSelected()) {
								if (textFiltraProduto.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "Informe o produto corretamente!");
								} else {
									FiltrarDataProdUmidade(dataInicial.getDate(), dataFim.getDate(),
											Integer.parseInt(textFiltraProduto.getText()),
											Float.parseFloat(textFiltraUmidade.getText()));
									quantidadeTotal();
								}
							} else {

								if (cbxData.isSelected() && cbxProduto.isSelected()) {
									if (textFiltraProduto.getText().equals("")) {
										JOptionPane.showMessageDialog(null, "Informe o produto corretamente!");
									} else {
										FiltrarDataProd(dataInicial.getDate(), dataFim.getDate(),
												Integer.parseInt(textFiltraProduto.getText()));
										quantidadeTotal();
									}
								} else {

									if (cbxData.isSelected() && cbxLote.isSelected()) {
										FiltrarDataLote(dataInicial.getDate(), dataFim.getDate(),
												textFiltraLote.getText());
										quantidadeTotal();
									} else {
										if (cbxData.isSelected() && cbxUmidade.isSelected()) {
											FiltrarDataUmidade(dataInicial.getDate(), dataFim.getDate(),
													Float.parseFloat(textFiltraUmidade.getText()));
											quantidadeTotal();
										} else {

											if (cbxData.isSelected()) {
												FiltrarData(dataInicial.getDate(), dataFim.getDate());
												quantidadeTotal();
											} else {
												preencheTabela();
												quantidadeTotal();
											}
										}
									}
								}
							}
						}
					}
				}

			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
				.createSequentialGroup()
				.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(cbxData)
								.addComponent(cbxProduto).addComponent(cbxLote).addComponent(cbxUmidade))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
								.createSequentialGroup()
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addComponent(dataInicial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblInicial))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(lblFinal)
										.addComponent(dataFim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
										.addComponent(textFiltraLote, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFiltraUmidade, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFiltraProduto, Alignment.LEADING, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_2.createSequentialGroup().addGap(94).addComponent(btnFiltrar)))
				.addContainerGap(45, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGap(26)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_2.createSequentialGroup().addComponent(btnFiltrar).addGap(21)
										.addComponent(lblInicial))
								.addComponent(lblFinal))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addComponent(dataFim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(dataInicial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxData))
						.addGap(4)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(cbxProduto)
								.addComponent(textFiltraProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(cbxLote).addComponent(
								textFiltraLote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(cbxUmidade)
								.addComponent(textFiltraUmidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(95, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);

		textFieldTotalRendimento = new JTextField();
		textFieldTotalRendimento.setEditable(false);
		textFieldTotalRendimento.setColumns(10);

		JLabel lblTotalRendimento = new JLabel("Quantidade Total");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addComponent(lblTotalRendimento).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(textFieldTotalRendimento, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE).addGap(2)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldTotalRendimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotalRendimento)).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() != -1) {

				Date data = (Date) datac.getDate();
				int produto = Integer.parseInt(CBProduto.getSelectedItem().toString());
				String descricao = textDescricao.getText();
				String lote = textLote.getText();
				String talhao = CBTalhao.getSelectedItem().toString();
				float quantidade = Float.parseFloat((textQuantidade.getText()));
				String qualidade = textQualidade.getText();
				String tipo = CBTipo.getSelectedItem().toString();
				float umidade = Float.parseFloat(textUmidade.getText());
				float rendimento = Float.parseFloat(textRendimento.getText());
				try {

					if (control.update(data, produto, descricao, lote, talhao, quantidade, qualidade, tipo, umidade,
							rendimento, selected)) {
						JOptionPane.showMessageDialog(rootPane, "Produto " + produto + " Alterado com Sucesso =) ");

						// CHAMADA DO MÉTODO QUE PREENCHE A TABELA DE PRODUTOS
						preencheTabela();
						quantidadeTotal();

						// Limpar os campos após a inserção do novo produto na tabela

						atualizar();
					} else {
						JOptionPane.showMessageDialog(rootPane, "Erro na persistência dos dados!");
					}
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(rootPane, "Reveja o SQL!");
				}
				} else {
					JOptionPane.showMessageDialog(rootPane, "Por favor selecione um Produto para Alterar!");
				}

			}
		});

		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				BeneficiadoDAO produtoDao = new BeneficiadoDAO();
				if (table.getSelectedRow() != -1) {
					int op = JOptionPane.showConfirmDialog(null, "Deseja Realmente excluir o Produto selecionado?",
							"Excluir Item", JOptionPane.YES_NO_OPTION);
					if (op != 2 && op != -1) {
						if (op == 0) {

							try {

								produtoDao.delete(selected);

								DefaultTableModel modelo = (DefaultTableModel) table.getModel();
								modelo.removeRow(selectedRow);
								quantidadeTotal();
								JOptionPane.showMessageDialog(null, "Produto Excluído!");

								atualizar();

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(rootPane, "Por favor selecione uma linha");
				}
			}

		});

		JLabel lblDatahora = new JLabel("Data");

		JLabel lblProduto = new JLabel("Produto");

		textDescricao = new JTextField();
		textDescricao.setColumns(10);

		JLabel lblLote = new JLabel("Lote");

		textLote = new JTextField();
		textLote.setColumns(10);

		textQuantidade = new JTextField();
		textQuantidade.setColumns(10);

		textRendimento = new JTextField();
		textRendimento.setColumns(10);

		JLabel lblNewLabel = new JLabel("Quantidade");

		JLabel lblUmidade = new JLabel("Umidade");

		JLabel lblRendimento = new JLabel("Rendimento");

		textQualidade = new JTextField();
		textQualidade.setColumns(10);

		textIdBeneficiado = new JTextField();
		textIdBeneficiado.setEditable(false);
		textIdBeneficiado.setColumns(10);

		JPanel panel_3 = new JPanel();

		textUmidade = new JTextField();
		textUmidade.setColumns(10);

		JLabel lblQualidade = new JLabel("Qualidade");

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();

			}
		});
		btnNewButton.setIcon(
				new ImageIcon("C:\\Users\\lequi\\OneDrive\\\u00C1rea de Trabalho\\ProjetoPCC\\Images\\atualizar.jpg"));

		JLabel lblVariedade = new JLabel("Talh\u00E3o");

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date data = (Date) datac.getDate();
				int produto = Integer.parseInt(CBProduto.getSelectedItem().toString());
				String descricao = textDescricao.getText();
				String lote = textLote.getText();
				String talhao = CBTalhao.getSelectedItem().toString();
				float quantidade = Float.parseFloat((textQuantidade.getText()));
				String qualidade = textQualidade.getText();
				String tipo = CBTipo.getSelectedItem().toString();
				float umidade = Float.parseFloat(textUmidade.getText());
				float rendimento = Float.parseFloat(textRendimento.getText());
				try {

					if (control.insert(data, produto, descricao, lote, talhao, quantidade, qualidade, tipo, umidade,
							rendimento, selected)) {
						JOptionPane.showMessageDialog(rootPane, quantidade + " Quilos do Produto " + produto
								+ " Foram adicionados ao estoque de Produtos Beneficiados.");
						// CHAMADA DO MÉTODO QUE PREENCHE A TABELA DE PRODUTOS
						preencheTabela();

						// Limpar os campos após a inserção do novo produto na tabela
						atualizar();

					} else {
						JOptionPane.showMessageDialog(rootPane,
								"O Produto " + CBProduto + " Já existe para o talhão " + talhao + "!");
					}
				} catch (HeadlessException | SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(rootPane, "Erro!");
				}
			}
		});

		JLabel lblTipo = new JLabel("Tipo");

		CBTipo.setModel(new DefaultComboBoxModel<Object>(new String[] { "", "Normal", "Fundo" }));

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblProduto)
						.addComponent(lblDatahora)
						.addComponent(lblLote)
						.addComponent(lblNewLabel)
						.addComponent(lblQualidade)
						.addComponent(lblRendimento)
						.addComponent(lblUmidade)
						.addComponent(lblVariedade)
						.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(CBTalhao, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(CBProduto, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textLote)
								.addComponent(textRendimento)
								.addComponent(textQualidade)
								.addComponent(textQuantidade)
								.addComponent(textUmidade))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textDescricao, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblTipo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(CBTipo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(datac, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(18)
								.addComponent(textIdBeneficiado, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
								.addComponent(btnAlterar, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnApagar, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(59, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)
						.addComponent(btnAlterar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNovo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnApagar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDatahora)
						.addComponent(textIdBeneficiado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(datac, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduto)
						.addComponent(textDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(CBProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLote)
								.addComponent(textLote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVariedade)
								.addComponent(CBTalhao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textUmidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUmidade)))
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textRendimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRendimento))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textQualidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQualidade))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(lblTipo)
						.addComponent(CBTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14))
		);
		CBProduto.setEditable(true);
		CBTalhao.setEditable(true);

		JButton btnVender = new JButton("Vender");
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (table.getSelectedRow() != -1) {
					float umidade2;

					umidade2 = Float.parseFloat(textUmidade.getText());

					if (umidade2 <=0 | textQualidade.getText().equals("") | CBTipo.getSelectedItem().equals("") ) {
						JOptionPane.showMessageDialog(null,
								"Por favor, atualize os campos Umidade, Qualidade e Tipo para este lote antes de vender. ");
					} else {

						VenderModel model = new VenderModel();
						model.setProduto((String) CBProduto.getSelectedItem());
						model.setDescricao((String) textDescricao.getText());
						model.setLote((String) textLote.getText());
						model.setTalhao((String) CBTalhao.getSelectedItem());
						model.setTipo((String) CBTipo.getSelectedItem());
						model.setIdBeneficiado((String) textIdBeneficiado.getText());
						model.setTipo((String) CBTipo.getSelectedItem());
						model.setQualidade((String) textQualidade.getText());
						model.setEstoque((String) textQuantidade.getText());
						VenderProduto vender = new VenderProduto();
						vender.pegarValores(model);
						vender.setVisible(true);

					}
				} else {
					JOptionPane.showMessageDialog(rootPane, "Por favor selecione um Produto para Vender!");
				}
			}
		});
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				gl_panel_3.createSequentialGroup().addContainerGap().addComponent(btnVender).addContainerGap(54,
						Short.MAX_VALUE)));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				gl_panel_3.createSequentialGroup().addGap(21)
						.addComponent(btnVender, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(23, Short.MAX_VALUE)));
		panel_3.setLayout(gl_panel_3);
		datac.setDateFormatString("MMMM dd, yyyy");
		panel.setLayout(gl_panel);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (table.getSelectedRow() != -1) {
					textIdBeneficiado.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					datac.setDate((Date) table.getValueAt(table.getSelectedRow(), 1));
					CBProduto.setSelectedItem(table.getValueAt(table.getSelectedRow(), 2).toString());
					textDescricao.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					textLote.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
					CBTalhao.setSelectedItem(table.getValueAt(table.getSelectedRow(), 5).toString());
					textUmidade.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
					textRendimento.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
					textQualidade.setText(table.getValueAt(table.getSelectedRow(), 8).toString());
					CBTipo.setSelectedItem(table.getValueAt(table.getSelectedRow(), 9).toString());
					textQuantidade.setText(table.getValueAt(table.getSelectedRow(), 10).toString());
				}
			}
		});
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Data", "Produto", "Descri\u00E7\u00E3o",
						"Lote", "Talh\u00E3o", "Umidade %", "Rendimento %", "Qualidade", "Tipo", "Quantidade Kg" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(28);
		table.getColumnModel().getColumn(1).setPreferredWidth(64);
		table.getColumnModel().getColumn(2).setPreferredWidth(57);
		table.getColumnModel().getColumn(3).setPreferredWidth(107);
		table.getColumnModel().getColumn(4).setPreferredWidth(49);
		table.getColumnModel().getColumn(5).setPreferredWidth(128);
		table.getColumnModel().getColumn(6).setPreferredWidth(60);
		table.getColumnModel().getColumn(8).setPreferredWidth(103);
		table.getColumnModel().getColumn(10).setMinWidth(25);
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

		preencheTabela();
		preencheComboTalhao();
		preencheComboProdutos();
		quantidadeTotal();

	}

	public void preencheComboProdutos() {
		try {
			List<CadastrarProdutoModel> listar = cpDAO.listarTodos();
			CBProduto.setModel(new DefaultComboBoxModel<Object>(new String[] { "" }));
			for (CadastrarProdutoModel cp : listar) {
				Object[] objeto = new Object[2];
				objeto[0] = cp.getCodigo();
				objeto[1] = cp.getDescricao();
				CBProduto.addItem(cp);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void preencheComboTalhao() {
		try {
			List<CadastrarTalhaoModel> listar = ctDAO.listarTodos();
			CBTalhao.setModel(new DefaultComboBoxModel<Object>(new String[] { "" }));
			for (CadastrarTalhaoModel ct : listar) {
				Object[] objeto = new Object[2];
				objeto[0] = ct.getTalhao();
				objeto[0] = ct.getVariedade();
				CBTalhao.addItem(ct);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void preencheTabela() {
		BeneficiadoDAO bDAO = new BeneficiadoDAO();
		try {
			List<BeneficiadoModel> lista = bDAO.consultaProdutos();
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			table.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));

			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (BeneficiadoModel p : lista) {
				Object[] objeto = new Object[11];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getLote();
				objeto[5] = p.getTalhao();
				objeto[6] = p.getUmidade();
				objeto[7] = p.getRendimento();
				objeto[8] = p.getQualidade();
				objeto[9] = p.getTipo();
				objeto[10] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {

				ListSelectionModel lsm = (ListSelectionModel) event.getSource();
				if (!lsm.isSelectionEmpty()) {
					selectedRow = lsm.getMinSelectionIndex();
					selected = (int) table.getValueAt(selectedRow, 0);
				}
			}
		});
	}

	public void FiltrarDataProdLoteUmidade(Date dataInicial, Date dataFim, int codigo, String lote, float umidade) {
		try {
			Date inicial;
			Date fim;
			inicial = new java.sql.Date(dataInicial.getTime());
			fim = new java.sql.Date(dataFim.getTime());
			List<BeneficiadoModel> lista = bDAO.consultaPorDataProdLoteUmidade(inicial, fim, codigo, lote, umidade);
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			table.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (BeneficiadoModel p : lista) {
				Object[] objeto = new Object[11];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getLote();
				objeto[5] = p.getTalhao();
				objeto[6] = p.getUmidade();
				objeto[7] = p.getRendimento();
				objeto[8] = p.getQualidade();
				objeto[9] = p.getTipo();
				objeto[10] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void FiltrarDataProdLote(Date dataInicial, Date dataFim, int codigo, String lote) {
		try {
			Date inicial;
			Date fim;
			inicial = new java.sql.Date(dataInicial.getTime());
			fim = new java.sql.Date(dataFim.getTime());
			List<BeneficiadoModel> lista = bDAO.consultaPorDataProdLote(inicial, fim, codigo, lote);
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			table.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (BeneficiadoModel p : lista) {
				Object[] objeto = new Object[11];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getLote();
				objeto[5] = p.getTalhao();
				objeto[6] = p.getUmidade();
				objeto[7] = p.getRendimento();
				objeto[8] = p.getQualidade();
				objeto[9] = p.getTipo();
				objeto[10] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void FiltrarDataLoteUmidade(Date dataInicial, Date dataFim, float umidade, String lote) {
		try {
			Date inicial;
			Date fim;
			inicial = new java.sql.Date(dataInicial.getTime());
			fim = new java.sql.Date(dataFim.getTime());
			List<BeneficiadoModel> lista = bDAO.consultaPorDataLoteUmidade(inicial, fim, umidade, lote);
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			table.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (BeneficiadoModel p : lista) {
				Object[] objeto = new Object[11];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getLote();
				objeto[5] = p.getTalhao();
				objeto[6] = p.getUmidade();
				objeto[7] = p.getRendimento();
				objeto[8] = p.getQualidade();
				objeto[9] = p.getTipo();
				objeto[10] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void FiltrarDataProd(Date dataInicial, Date dataFim, int cod, String lote) {
		try {
			Date inicial;
			Date fim;
			inicial = new java.sql.Date(dataInicial.getTime());
			fim = new java.sql.Date(dataFim.getTime());
			List<BeneficiadoModel> lista = bDAO.consultaPorDataLoteUmidade(inicial, fim, cod, lote);
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			table.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (BeneficiadoModel p : lista) {
				Object[] objeto = new Object[11];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getLote();
				objeto[5] = p.getTalhao();
				objeto[6] = p.getUmidade();
				objeto[7] = p.getRendimento();
				objeto[8] = p.getQualidade();
				objeto[9] = p.getTipo();
				objeto[10] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void FiltrarDataProdUmidade(Date dataInicial, Date dataFim, int cod, float umidade) {
		try {
			Date inicial;
			Date fim;
			inicial = new java.sql.Date(dataInicial.getTime());
			fim = new java.sql.Date(dataFim.getTime());
			List<BeneficiadoModel> lista = bDAO.consultaPorDataProdUmidade(inicial, fim, cod, umidade);
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			table.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (BeneficiadoModel p : lista) {
				Object[] objeto = new Object[11];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getLote();
				objeto[5] = p.getTalhao();
				objeto[6] = p.getUmidade();
				objeto[7] = p.getRendimento();
				objeto[8] = p.getQualidade();
				objeto[9] = p.getTipo();
				objeto[10] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void FiltrarDataProd(Date dataInicial, Date dataFim, int cod) {
		try {
			Date inicial;
			Date fim;
			inicial = new java.sql.Date(dataInicial.getTime());
			fim = new java.sql.Date(dataFim.getTime());
			List<BeneficiadoModel> lista = bDAO.consultaPorDataProd(inicial, fim, cod);
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			table.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (BeneficiadoModel p : lista) {
				Object[] objeto = new Object[11];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getLote();
				objeto[5] = p.getTalhao();
				objeto[6] = p.getUmidade();
				objeto[7] = p.getRendimento();
				objeto[8] = p.getQualidade();
				objeto[9] = p.getTipo();
				objeto[10] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void FiltrarDataLote(Date dataInicial, Date dataFim, String lote) {
		try {
			Date inicial;
			Date fim;
			inicial = new java.sql.Date(dataInicial.getTime());
			fim = new java.sql.Date(dataFim.getTime());
			List<BeneficiadoModel> lista = bDAO.consultaPorDataLote(inicial, fim, lote);
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			table.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (BeneficiadoModel p : lista) {
				Object[] objeto = new Object[11];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getLote();
				objeto[5] = p.getTalhao();
				objeto[6] = p.getUmidade();
				objeto[7] = p.getRendimento();
				objeto[8] = p.getQualidade();
				objeto[9] = p.getTipo();
				objeto[10] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void FiltrarDataUmidade(Date dataInicial, Date dataFim, float umidade) {
		try {
			Date inicial;
			Date fim;
			inicial = new java.sql.Date(dataInicial.getTime());
			fim = new java.sql.Date(dataFim.getTime());
			List<BeneficiadoModel> lista = bDAO.consultaPorDataUmidade(inicial, fim, umidade);
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			table.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (BeneficiadoModel p : lista) {
				Object[] objeto = new Object[11];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getLote();
				objeto[5] = p.getTalhao();
				objeto[6] = p.getUmidade();
				objeto[7] = p.getRendimento();
				objeto[8] = p.getQualidade();
				objeto[9] = p.getTipo();
				objeto[10] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void FiltrarData(Date dataInicial, Date dataFim) {
		try {
			Date inicial;
			Date fim;
			inicial = new java.sql.Date(dataInicial.getTime());
			fim = new java.sql.Date(dataFim.getTime());
			List<BeneficiadoModel> lista = bDAO.consultaPorData(inicial, fim);
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			table.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (BeneficiadoModel p : lista) {
				Object[] objeto = new Object[11];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getLote();
				objeto[5] = p.getTalhao();
				objeto[6] = p.getUmidade();
				objeto[7] = p.getRendimento();
				objeto[8] = p.getQualidade();
				objeto[9] = p.getTipo();
				objeto[10] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void quantidadeTotal() {
		float soma = 0;
		if (table.getRowCount() == 0) {
			textFieldTotalRendimento.setText("");
		} else {
			for (int u = 0; u < table.getRowCount(); u++) {
				soma = soma + Float.valueOf(table.getValueAt(u, 10).toString());
				textFieldTotalRendimento.setText(String.valueOf(soma));
			}
		}
	}

	public void atualizar() {
		preencheTabela();
		quantidadeTotal();
		textIdBeneficiado.setText("");
		CBTipo.setSelectedItem(null);
		CBTalhao.setSelectedItem(null);
		CBProduto.setSelectedItem(null);
		textDescricao.setText("");
		textLote.setText("");
		textQuantidade.setText("");
		textQualidade.setText("");
		textUmidade.setText("");
		textRendimento.setText("");
		textFiltraProduto.setText("");
		textFiltraLote.setText("");
		textFiltraUmidade.setText("");
		cbxProduto.setSelected(false);
		cbxLote.setSelected(false);
		cbxUmidade.setSelected(false);

	}

}