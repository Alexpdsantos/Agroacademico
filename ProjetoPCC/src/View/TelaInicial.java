package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

import Controller.ProdutoControl;
import DAO.CadastrarProdutoDAO;
import DAO.CadastrarTalhaoDAO;
import DAO.ProdutoDAO;
import Model.BeneficiarModel;
import Model.CadastrarProdutoModel;
import Model.CadastrarTalhaoModel;
import Model.ProdutoModel;

public class TelaInicial extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final JComponent CBoxProduto = null;
	ArrayList<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
	ProdutoDAO pDAO = new ProdutoDAO();
	ProdutoControl control = new ProdutoControl();
	CadastrarProdutoDAO cpDAO = new CadastrarProdutoDAO();
	CadastrarTalhaoDAO ctDAO = new CadastrarTalhaoDAO();
	Beneficiar beneficiamento = new Beneficiar();

	private JTable tableEstoque;
	private JTextField textDescricao1;
	private JTextField textFiltraLote;
	private JTextField textFiltraProd;
	private JTextField textFiltraTalhao;
	private JTextField textLote;
	private JTextField textQuantidade;
	private JTextField textQtdTotal;
	private JComboBox<Object> CBProduto = new JComboBox<Object>();
	private JComboBox<Object> CBTalhao = new JComboBox<Object>();
	JDateChooser dateChooser = new JDateChooser();
	private JCheckBox cbxProduto = new JCheckBox("Produto");
	private JCheckBox cbxTalhao = new JCheckBox("Talhão");
	private JCheckBox cbxLote = new JCheckBox("Lote");
	int selected;
	int selectedRow;
	private JTextField textId;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
					frame.setExtendedState(MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public TelaInicial() throws SQLException {
		getContentPane().setBackground(SystemColor.menu);
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				atualizar();

			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});

		setTitle("AGROACAD\u00CAMICO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1500, 778);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Gadugi", menuBar.getFont().getStyle(), menuBar.getFont().getSize()));
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setForeground(SystemColor.desktop);
		setJMenuBar(menuBar);

		JMenu mnLogin = new JMenu("Login");
		mnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(rootPane, "Você Já está logado no sistema!");

			}
		});
		menuBar.add(mnLogin);

		JLabel label = new JLabel("       ");
		menuBar.add(label);

		JMenu mnCadastrar = new JMenu("Cadastros");
		menuBar.add(mnCadastrar);

		JMenuItem mntmCadastrarTalho = new JMenuItem("Cadastrar Talh\u00E3o");
		mntmCadastrarTalho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarTalhao cadTalhao = new CadastrarTalhao();
				cadTalhao.setVisible(true);

			}

		});
		mnCadastrar.add(mntmCadastrarTalho);

		JMenuItem mntmCadastrarProduto = new JMenuItem("Cadastrar Produto");
		mntmCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new CadastrarProduto().setVisible(true);
			}
		});

		mnCadastrar.add(mntmCadastrarProduto);

		JLabel label_1 = new JLabel("       ");
		menuBar.add(label_1);

		JMenu mnBeneficiar = new JMenu("Beneficiamento");
		menuBar.add(mnBeneficiar);

		JMenuItem mntmBeneficiarCaf = new JMenuItem("Processo de Beneficiamento");
		mntmBeneficiarCaf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Beneficiar().setVisible(true);

			}
		});
		mnBeneficiar.add(mntmBeneficiarCaf);

		JMenuItem mntmCafsBeneficiados = new JMenuItem("Estoque de produtos Beneficiados");
		mntmCafsBeneficiados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				new EstoqueBeneficiado().setVisible(true);
			}
		});
		mnBeneficiar.add(mntmCafsBeneficiados);

		JLabel label_2 = new JLabel("       ");
		menuBar.add(label_2);

		JMenu mnVendas = new JMenu("Vendas");
		menuBar.add(mnVendas);

		JMenuItem mntmHistoricoDeVendas = new JMenuItem("Historico de Vendas");
		mntmHistoricoDeVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new historicoVendas().setVisible(true);
			}
		});
		mnVendas.add(mntmHistoricoDeVendas);

		JMenu mnSair = new JMenu("Sair");
		mnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				int op = JOptionPane.showConfirmDialog(null, "Você Deseja Realmente Sair?", "Sair o.o'",
						JOptionPane.YES_NO_OPTION);
				if (op != 2 && op != -1) {
					if (op == 0) {
						JOptionPane.showMessageDialog(null, "Aplição Encerrada, Até logo...");
						dispose();
					}
				}
			}
		});

		JMenu mnRelatrios = new JMenu("Relat\u00F3rios");
		mnRelatrios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});
		mnRelatrios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		JLabel label_3 = new JLabel("       ");
		menuBar.add(label_3);
		menuBar.add(mnRelatrios);

		JLabel label_4 = new JLabel("       ");
		menuBar.add(label_4);
		menuBar.add(mnSair);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(64, 64, 64), null, null, null));

		JDateChooser dataFim = new JDateChooser();
		dataFim.setDateFormatString("MMMM dd, yyyy");

		JDateChooser dataInicial = new JDateChooser();
		dataInicial.setDateFormatString("MMMM dd, yyyy");

		textFiltraProd = new JTextField();
		textFiltraProd.setColumns(10);

		textFiltraLote = new JTextField();
		textFiltraLote.setColumns(10);

		textFiltraTalhao = new JTextField();
		textFiltraTalhao.setColumns(10);

		JLabel lblFinal = new JLabel("Final");

		JLabel lblInicial = new JLabel("Inicial");

		JLabel lblFiltros = new JLabel("Filtros");
		lblFiltros.setFont(new Font("Sylfaen", Font.PLAIN, 20));

		JCheckBox cbxData = new JCheckBox("Data");
		cbxData.setEnabled(false);
		cbxData.setSelected(true);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (cbxData.isSelected() && cbxProduto.isSelected() && cbxLote.isSelected() && cbxTalhao.isSelected()) {
					if (textFiltraProd.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe o produto corretamente!");
					} else {
						FiltrarDataProdLoteTalhao(dataInicial.getDate(), dataFim.getDate(),
								Integer.parseInt(textFiltraProd.getText()), textFiltraLote.getText(),
								textFiltraTalhao.getText());
						valorTotal();
					}
				} else {

					if (cbxData.isSelected() && cbxProduto.isSelected() && cbxLote.isSelected()) {
						if (textFiltraProd.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Informe o produto corretamente!");
						} else {
							FiltrarDataProdLote(dataInicial.getDate(), dataFim.getDate(),
									Integer.parseInt(textFiltraProd.getText()), textFiltraLote.getText());
							valorTotal();
						}
					} else {
						if (cbxData.isSelected() && cbxTalhao.isSelected() && cbxLote.isSelected()) {
							FiltrarDataTalhaoLote(dataInicial.getDate(), dataFim.getDate(), textFiltraTalhao.getText(),
									textFiltraLote.getText());
							valorTotal();
						} else {
							if (cbxData.isSelected() && cbxProduto.isSelected() && cbxTalhao.isSelected()) {
								if (textFiltraProd.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "Informe o produto corretamente!");
								} else {
									FiltrarDataProdTalhao(dataInicial.getDate(), dataFim.getDate(),
											Integer.parseInt(textFiltraProd.getText()), textFiltraTalhao.getText());
									valorTotal();
								}
							} else {

								if (cbxData.isSelected() && cbxProduto.isSelected()) {
									if (textFiltraProd.getText().equals("")) {
										JOptionPane.showMessageDialog(null, "Informe o produto corretamente!");
									} else {
										FiltrarDataProd(dataInicial.getDate(), dataFim.getDate(),
												Integer.parseInt(textFiltraProd.getText()));
										valorTotal();
									}
								} else {

									if (cbxData.isSelected() && cbxLote.isSelected()) {
										FiltrarDataLote(dataInicial.getDate(), dataFim.getDate(),
												textFiltraLote.getText());
										valorTotal();
									} else {
										if (cbxData.isSelected() && cbxTalhao.isSelected()) {
											FiltrarDataTalhao(dataInicial.getDate(), dataFim.getDate(),
													textFiltraTalhao.getText());
											valorTotal();
										} else {

											if (cbxData.isSelected()) {

												FiltrarData(dataInicial.getDate(), dataFim.getDate());
												valorTotal();
											} else {
												if (cbxProduto.isSelected()) {
													if (textFiltraProd.getText().equals("")) {
														JOptionPane.showMessageDialog(null,
																"Informe o produto corretamente!");
													} else {
														FiltrarProd(Integer.parseInt(textFiltraProd.getText()));
														valorTotal();
													}
												}

												else {
													if (cbxTalhao.isSelected()) {
														FiltrarTalhao(textFiltraTalhao.getText());
														valorTotal();
													} else {
														if (cbxLote.isSelected()) {
															FiltrarLote(textFiltraLote.getText());
															valorTotal();
														} else {
															preencheTabela();
															valorTotal();
														}
													}
												}
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

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(25)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(btnFiltrar)
								.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(cbxData)
												.addComponent(cbxProduto).addComponent(cbxTalhao).addComponent(cbxLote))
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup().addGap(84)
										.addComponent(lblFiltros, GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup().addGap(13)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblInicial, GroupLayout.PREFERRED_SIZE, 41,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(textFiltraTalhao, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(textFiltraProd, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(textFiltraLote, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(Alignment.TRAILING,
														gl_panel.createSequentialGroup()
																.addComponent(dataInicial, GroupLayout.PREFERRED_SIZE,
																		151, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblFinal)
												.addComponent(dataFim, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(153, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
												.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblFiltros).addComponent(btnFiltrar))
												.addGap(58))
										.addComponent(lblInicial))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(cbxData)
										.addComponent(dataInicial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup().addComponent(lblFinal)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(dataFim,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(12)
				.addGroup(gl_panel
						.createParallelGroup(Alignment.TRAILING).addComponent(cbxProduto).addComponent(textFiltraProd,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel
						.createParallelGroup(Alignment.LEADING).addComponent(cbxLote).addComponent(textFiltraLote,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(8)
				.addGroup(gl_panel
						.createParallelGroup(Alignment.BASELINE).addComponent(textFiltraTalhao,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxTalhao))
				.addGap(35)));
		panel.setLayout(gl_panel);

		tableEstoque = new JTable();
		tableEstoque.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (tableEstoque.getSelectedRow() != -1) {

					textId.setText(tableEstoque.getValueAt(tableEstoque.getSelectedRow(), 0).toString());
					dateChooser.setDate((Date) tableEstoque.getValueAt(tableEstoque.getSelectedRow(), 1));
					CBProduto.setSelectedItem(tableEstoque.getValueAt(tableEstoque.getSelectedRow(), 2).toString());
					textDescricao1.setText(tableEstoque.getValueAt(tableEstoque.getSelectedRow(), 3).toString());
					CBTalhao.setSelectedItem(tableEstoque.getValueAt(tableEstoque.getSelectedRow(), 4).toString());
					textLote.setText(tableEstoque.getValueAt(tableEstoque.getSelectedRow(), 5).toString());
					textQuantidade.setText(tableEstoque.getValueAt(tableEstoque.getSelectedRow(), 6).toString());

				}

			}
		});
		tableEstoque.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, },
				new String[] { "id", "Data", "Produto", "Descri\u00E7\u00E3o", "Talh\u00E3o", "Lote", "Quantidade" }));
		tableEstoque.getColumnModel().getColumn(0).setPreferredWidth(26);
		tableEstoque.getColumnModel().getColumn(1).setPreferredWidth(82);
		tableEstoque.getColumnModel().getColumn(1).setMinWidth(50);
		tableEstoque.getColumnModel().getColumn(2).setPreferredWidth(85);
		tableEstoque.getColumnModel().getColumn(3).setPreferredWidth(257);
		tableEstoque.getColumnModel().getColumn(4).setPreferredWidth(111);
		tableEstoque.getColumnModel().getColumn(4).setMinWidth(40);
		tableEstoque.getColumnModel().getColumn(5).setPreferredWidth(110);
		tableEstoque.getColumnModel().getColumn(6).setPreferredWidth(125);
		tableEstoque.getColumnModel().getColumn(6).setMinWidth(25);
		scrollPane.setViewportView(tableEstoque);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, new Color(255, 255, 255), null, null));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.WHITE, null, null));

		JLabel lblAgroacadmico = new JLabel("AgroAcad\u00EAmico ");
		lblAgroacadmico.setForeground(new Color(0, 0, 139));
		lblAgroacadmico.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 50));
		lblAgroacadmico.setBackground(Color.WHITE);

		JLabel lblNewLabel_1 = new JLabel("Gerenciamento de Estoque");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1350, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE)
								.addGap(134)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 129, Short.MAX_VALUE).addComponent(panel_2,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(
								groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED, 1012, Short.MAX_VALUE).addGroup(
												groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(lblAgroacadmico)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(lblNewLabel_1).addGap(15)))))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
								.createSequentialGroup()
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblAgroacadmico).addGap(31)).addGroup(
										groupLayout
												.createSequentialGroup().addComponent(panel_1,
														GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
												.addContainerGap())))));

		JLabel lblTotal = new JLabel("Quantidade Total");

		textQtdTotal = new JTextField();
		textQtdTotal.setEditable(false);
		textQtdTotal.setColumns(10);

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGap(4).addComponent(lblTotal)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(textQtdTotal, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_2
				.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup().addGap(8)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
										.addComponent(textQtdTotal, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTotal))));
		panel_2.setLayout(gl_panel_2);

		textQuantidade = new JTextField();
		textQuantidade.setColumns(10);

		JLabel lblNewLabel = new JLabel("Quantidade");

		textLote = new JTextField();
		textLote.setColumns(10);

		JLabel lblLote = new JLabel("Lote");

		JLabel lblTalho = new JLabel("Talh\u00E3o");

		JLabel lblProduto = new JLabel("Produto");

		textDescricao1 = new JTextField();

		textDescricao1.setColumns(10);

		JLabel lblData = new JLabel("Data");

		JButton btnNewButton = new JButton("Novo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Date data = (Date) dateChooser.getDate();
				int produto = Integer.parseInt(CBProduto.getSelectedItem().toString());
				String descricao = textDescricao1.getText();
				String lote = textLote.getText();
				String talhao = CBTalhao.getSelectedItem().toString();
				float quantidade = Float.parseFloat(textQuantidade.getText());

				try {
					if (control.insert(data, produto, descricao, lote, talhao, quantidade)) {
						JOptionPane.showMessageDialog(rootPane,
								"O Lote " + lote + " foi cadastrado com sucesso para o Produto " + produto + ".");
						// CHAMADA DO MÉTODO QUE PREENCHE A TABELA DE PRODUTOS
						preencheTabela();

						// Limpar os campos após a inserção do novo produto na tabela e atualizar
						valorTotal();
						// atualizar();
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

		// CHAMADA DOS MÉTODOS DE PREENCHIMENTO
		preencheTabela();
		preencheComboTalhao();
		preencheComboProdutos();
		valorTotal();

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableEstoque.getSelectedRow() != -1) {

					Date data = (Date) dateChooser.getDate();
					int produto = Integer.parseInt(CBProduto.getSelectedItem().toString());
					String descricao = textDescricao1.getText();
					String lote = textLote.getText();
					String talhao = CBTalhao.getSelectedItem().toString();
					float quantidade = Float.parseFloat(textQuantidade.getText());

					try {

						if (control.update(data, produto, descricao, lote, talhao, quantidade, selected)) {
							JOptionPane.showMessageDialog(rootPane, "Produto " + produto + " Alterado com Sucesso =) ");

							// CHAMADA DO MÉTODO QUE PREENCHE A TABELA DE PRODUTOS
							preencheTabela();

							// Limpar os campos após a inserção do novo produto na tabela e atualizar
							valorTotal();
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
				ProdutoDAO produtoDao = new ProdutoDAO();
				if (tableEstoque.getSelectedRow() != -1) {
					int op = JOptionPane.showConfirmDialog(null, "Deseja Realmente excluir o Produto selecionado?",
							"Excluir Item", JOptionPane.YES_NO_OPTION);
					if (op != 2 && op != -1) {
						if (op == 0) {

							try {

								produtoDao.delete(selected);

								DefaultTableModel modelo = (DefaultTableModel) tableEstoque.getModel();
								modelo.removeRow(selectedRow);

								// Limpar os campos após a inserção do novo produto na tabela
								valorTotal();
								atualizar();
								JOptionPane.showMessageDialog(null, "Produto Excluído!");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,
										"Produto não pode ser Excluído, pois está sendo Beneficiado!");
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(rootPane, "Por favor selecione um Produto para excluir");
				}
			}
		});

		CBProduto.setEditable(true);
		CBProduto.setMaximumRowCount(15);

		CBTalhao.setEditable(true);
		CBTalhao.setMaximumRowCount(15);

		JButton btnAtualizar = new JButton("");
		btnAtualizar.setIcon(
				new ImageIcon("C:\\Users\\lequi\\OneDrive\\\u00C1rea de Trabalho\\ProjetoPCC\\Images\\atualizar.jpg"));
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizar();

			}
		});

		JPanel panel_3 = new JPanel();

		textId = new JTextField();
		textId.setEnabled(false);
		textId.setEditable(false);
		textId.setColumns(10);

		JLabel lblId = new JLabel("id");
		lblId.setEnabled(false);

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
								.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblId).addComponent(lblLote).addComponent(lblTalho).addComponent(
												lblProduto)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(
												gl_panel_1.createSequentialGroup().addGap(33).addComponent(lblData))
												.addComponent(lblNewLabel)))
								.addGap(18)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 152,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1
												.createParallelGroup(Alignment.TRAILING)
												.addComponent(CBTalhao, Alignment.LEADING, 0, 125, Short.MAX_VALUE)
												.addComponent(textQuantidade, GroupLayout.DEFAULT_SIZE, 125,
														Short.MAX_VALUE)
												.addComponent(CBProduto, Alignment.LEADING, 0, 125, Short.MAX_VALUE)
												.addComponent(textLote, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														125, Short.MAX_VALUE)
												.addComponent(textId, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 32,
														GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
														.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 122,
																Short.MAX_VALUE)
														.addComponent(textDescricao1, GroupLayout.PREFERRED_SIZE, 122,
																GroupLayout.PREFERRED_SIZE))))
								.addGap(48))
								.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 82,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(btnAlterar, GroupLayout.PREFERRED_SIZE, 90,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(btnApagar, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(24)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_1
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnApagar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAtualizar, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(btnAlterar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblId))
				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING).addComponent(lblData).addComponent(
						dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(CBProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(textDescricao1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProduto))
				.addGap(18)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_1.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(CBTalhao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTalho))
						.addGap(24)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(textLote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLote))
						.addGap(21)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(textQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel)))
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
				.addGap(81)));
		dateChooser.setDateFormatString("MMMM dd, yyyy");

		JButton btnNewButton_1 = new JButton("Beneficiar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tableEstoque.getSelectedRow() != -1) {

					BeneficiarModel model = new BeneficiarModel();
					model.setIdProduto((String) textId.getText());
					model.setProduto((String) CBProduto.getSelectedItem());
					model.setDescricao((String) textDescricao1.getText());
					model.setLote((String) textLote.getText());
					model.setTalhao((String) CBTalhao.getSelectedItem());
					Beneficiar benef = new Beneficiar();
					benef.puxarValores(model);
					benef.setVisible(true);
					benef.textDescricao.setText(textDescricao1.getText());
				} else {
					JOptionPane.showMessageDialog(null, "Por Favor, selecine um Produto para Beneficiar!");
				}
			}
		});
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel_3.createSequentialGroup().addContainerGap(22, Short.MAX_VALUE).addComponent(btnNewButton_1)
						.addGap(20)));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup().addGap(22)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(32, Short.MAX_VALUE)));
		panel_3.setLayout(gl_panel_3);
		panel_1.setLayout(gl_panel_1);
		getContentPane().setLayout(groupLayout);

		/*CBProduto.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				CadastrarProdutoModel produto = (CadastrarProdutoModel) CBProduto.getSelectedItem();
				if (produto == null) {
					textDescricao1.setText("");
				} else {
					textDescricao1.setText(produto.getDescricao());
				}
			}
		});*/
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
		try {
			List<ProdutoModel> lista = pDAO.consultaProdutos();
			DefaultTableModel modelo = (DefaultTableModel) tableEstoque.getModel();
			tableEstoque.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (ProdutoModel p : lista) {
				Object[] objeto = new Object[7];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getTalhao();
				objeto[5] = p.getLote();
				objeto[6] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		tableEstoque.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				ListSelectionModel lsm = (ListSelectionModel) event.getSource();
				if (!lsm.isSelectionEmpty()) {
					selectedRow = lsm.getMinSelectionIndex();
					selected = (int) tableEstoque.getValueAt(selectedRow, 0);
				}
			}
		});
	}

	public void FiltrarDataProdLoteTalhao(Date dataInicial, Date dataFim, int codigo, String lote, String talhao) {
		try {
			Date inicial;
			Date fim;
			inicial = new java.sql.Date(dataInicial.getTime());
			fim = new java.sql.Date(dataFim.getTime());
			List<ProdutoModel> lista = pDAO.consultaPorDataProdLoteTalhao(inicial, fim, codigo, lote, talhao);
			DefaultTableModel modelo = (DefaultTableModel) tableEstoque.getModel();
			tableEstoque.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (ProdutoModel p : lista) {
				Object[] objeto = new Object[7];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getTalhao();
				objeto[5] = p.getLote();
				objeto[6] = p.getQuantidade();
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
			List<ProdutoModel> lista = pDAO.consultaPorDataProdLote(inicial, fim, codigo, lote);
			DefaultTableModel modelo = (DefaultTableModel) tableEstoque.getModel();
			tableEstoque.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (ProdutoModel p : lista) {
				Object[] objeto = new Object[7];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getTalhao();
				objeto[5] = p.getLote();
				objeto[6] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void FiltrarDataProdTalhao(Date dataInicial, Date dataFim, int codigo, String talhao) {
		try {
			Date inicial;
			Date fim;
			inicial = new java.sql.Date(dataInicial.getTime());
			fim = new java.sql.Date(dataFim.getTime());
			List<ProdutoModel> lista = pDAO.consultaPorDataProdTalhao(inicial, fim, codigo, talhao);
			DefaultTableModel modelo = (DefaultTableModel) tableEstoque.getModel();
			tableEstoque.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (ProdutoModel p : lista) {
				Object[] objeto = new Object[7];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getTalhao();
				objeto[5] = p.getLote();
				objeto[6] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void FiltrarDataTalhaoLote(Date dataInicial, Date dataFim, String talhao, String lote) {
		try {
			Date inicial;
			Date fim;
			inicial = new java.sql.Date(dataInicial.getTime());
			fim = new java.sql.Date(dataFim.getTime());
			List<ProdutoModel> lista = pDAO.consultaPorDataTalhaoLote(inicial, fim, talhao, lote);
			DefaultTableModel modelo = (DefaultTableModel) tableEstoque.getModel();
			tableEstoque.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (ProdutoModel p : lista) {
				Object[] objeto = new Object[7];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getTalhao();
				objeto[5] = p.getLote();
				objeto[6] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void FiltrarDataProd(Date dataInicial, Date dataFim, int codigo) {
		try {
			Date inicial;
			Date fim;
			inicial = new java.sql.Date(dataInicial.getTime());
			fim = new java.sql.Date(dataFim.getTime());
			List<ProdutoModel> lista = pDAO.consultaPorDataProd(inicial, fim, codigo);
			DefaultTableModel modelo = (DefaultTableModel) tableEstoque.getModel();
			tableEstoque.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (ProdutoModel p : lista) {
				Object[] objeto = new Object[7];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getTalhao();
				objeto[5] = p.getLote();
				objeto[6] = p.getQuantidade();
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
			List<ProdutoModel> lista = pDAO.consultaPorDataLote(inicial, fim, lote);
			DefaultTableModel modelo = (DefaultTableModel) tableEstoque.getModel();
			tableEstoque.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (ProdutoModel p : lista) {
				Object[] objeto = new Object[7];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getTalhao();
				objeto[5] = p.getLote();
				objeto[6] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void FiltrarDataTalhao(Date dataInicial, Date dataFim, String talhao) {
		try {
			Date inicial;
			Date fim;
			inicial = new java.sql.Date(dataInicial.getTime());
			fim = new java.sql.Date(dataFim.getTime());
			List<ProdutoModel> lista = pDAO.consultaPorDataTalhao(inicial, fim, talhao);
			DefaultTableModel modelo = (DefaultTableModel) tableEstoque.getModel();
			tableEstoque.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (ProdutoModel p : lista) {
				Object[] objeto = new Object[7];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getTalhao();
				objeto[5] = p.getLote();
				objeto[6] = p.getQuantidade();
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
			List<ProdutoModel> lista = pDAO.consultaPorData(inicial, fim);
			DefaultTableModel modelo = (DefaultTableModel) tableEstoque.getModel();
			tableEstoque.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (ProdutoModel p : lista) {
				Object[] objeto = new Object[7];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getTalhao();
				objeto[5] = p.getLote();
				objeto[6] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void FiltrarProd(int codigo) {
		try {
			List<ProdutoModel> lista = pDAO.consultaPorProd(codigo);
			DefaultTableModel modelo = (DefaultTableModel) tableEstoque.getModel();
			tableEstoque.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (ProdutoModel p : lista) {
				Object[] objeto = new Object[7];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getTalhao();
				objeto[5] = p.getLote();
				objeto[6] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void FiltrarLote(String lote) {
		try {
			List<ProdutoModel> lista = pDAO.consultaPorLote(lote);
			DefaultTableModel modelo = (DefaultTableModel) tableEstoque.getModel();
			tableEstoque.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (ProdutoModel p : lista) {
				Object[] objeto = new Object[7];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getTalhao();
				objeto[5] = p.getLote();
				objeto[6] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void FiltrarTalhao(String talhao) {
		try {
			List<ProdutoModel> lista = pDAO.consultaPorTalhao(talhao);
			DefaultTableModel modelo = (DefaultTableModel) tableEstoque.getModel();
			tableEstoque.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (ProdutoModel p : lista) {
				Object[] objeto = new Object[7];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getTalhao();
				objeto[5] = p.getLote();
				objeto[6] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void valorTotal() {
		float soma = 0;
		if (tableEstoque.getRowCount() == 0) {
			textQtdTotal.setText("");
		} else {
			for (int u = 0; u < tableEstoque.getRowCount(); u++) {
				soma = soma + Float.valueOf(tableEstoque.getValueAt(u, 6).toString());
				textQtdTotal.setText(String.valueOf(soma));
			}
		}
	}

	public void atualizar() {
		try {
			pDAO.deleteZero();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		preencheTabela();
		valorTotal();
		preencheComboProdutos();
		preencheComboTalhao();
		textId.setText("");
		CBProduto.setSelectedItem(null);
		CBTalhao.setSelectedItem(null);
		textFiltraTalhao.setText("");
		textFiltraProd.setText("");
		textFiltraLote.setText("");
		textDescricao1.setText("");
		textLote.setText("");
		textQuantidade.setText("");
		cbxProduto.setSelected(false);
		cbxLote.setSelected(false);
		cbxTalhao.setSelected(false);
	}
}