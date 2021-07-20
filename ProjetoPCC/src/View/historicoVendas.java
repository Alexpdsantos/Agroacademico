package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import Controller.VenderControl;
import DAO.VenderDAO;
import Model.VenderModel;

public class historicoVendas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textFiltraProduto;
	private JTextField textFiltraLote;
	private JTextField textFiltraPreco;
	private JTextField textFiltraQualidade;
	private JTextField textQuantTotal;
	VenderDAO vDao = new VenderDAO();
	VenderControl control = new VenderControl();

	int selected;
	int selectedRow;
	private JTextField textProduto;
	private JTextField textDescricao;
	private JTextField textTalhao;
	private JTextField textLote;
	private JTextField textTipo;
	private JTextField textQualidade;
	private JTextField textPrecoKg;
	private JTextField textDesconto;
	private JTextField textValorTotal;
	private JTextField textCliente;
	private JTextField textQuantidade;
	private JTextField textId;

	private JCheckBox cbxPreco = new JCheckBox("Pre\u00E7o");
	private JCheckBox cbxQualidade = new JCheckBox("Qualidade");
	private JCheckBox cbxLote = new JCheckBox("Lote");
	private JCheckBox cbxProduto = new JCheckBox("Produto");
	private JCheckBox cbxData = new JCheckBox("Data");

	private JDateChooser dataFinal = new JDateChooser();

	private JDateChooser dataInicial = new JDateChooser();

	private JDateChooser dataVenda = new JDateChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					historicoVendas frame = new historicoVendas();
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
	public historicoVendas() {
		setTitle("Historico de Vendas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1112, 695);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, null, null, null));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, null, null, null));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, null, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(25, Short.MAX_VALUE)));

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String idBeneficiado = null;
				String produto = textProduto.getText();
				String descricao = textDescricao.getText();
				String talhao = textTalhao.getText();
				String lote = textLote.getText();
				String tipo = textTipo.getText();
				String qualidade = textQualidade.getText();
				float quantidade = Float.parseFloat((textQuantidade.getText()));
				double precoKg = Double.parseDouble(textPrecoKg.getText());
				;
				int desconto = Integer.parseInt(textDesconto.getText());
				double valorTotal = Double.parseDouble(textValorTotal.getText());
				String cliente = textCliente.getText();
				Date data = (Date) dataVenda.getDate();
				try {

					if (control.insert(idBeneficiado, produto, descricao, talhao, lote, tipo, qualidade, quantidade,
							precoKg, desconto, valorTotal, cliente, data)) {
						JOptionPane.showMessageDialog(rootPane, quantidade + " Quilos do Produto " + produto
								+ " Foram adicionados ao estoque de Produtos Beneficiados.");
						// CHAMADA DO MÉTODO QUE PREENCHE A TABELA DE PRODUTOS

						valorTotal();

						// Limpar os campos após a inserção do novo produto na tabela
						atualizar();

					} else {
						JOptionPane.showMessageDialog(rootPane, "Problema ao Inserir este produto ao estoque!");
					}
				} catch (HeadlessException | SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(rootPane, "Erro!");
				}

			}
		});

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (table.getSelectedRow() != -1) {

					// String idBeneficiado = textId.getText();
					String produto = textProduto.getText();
					String descricao = textDescricao.getText();
					String talhao = textTalhao.getText();
					String lote = textLote.getText();
					String tipo = textTipo.getText();
					String qualidade = textQualidade.getText();
					float quantidade = Float.parseFloat((textQuantidade.getText()));
					double precoKg = Double.parseDouble(textPrecoKg.getText());
					;
					int desconto = Integer.parseInt(textDesconto.getText());
					double valorTotal = Double.parseDouble(textValorTotal.getText());
					String cliente = textCliente.getText();
					Date data = (Date) dataVenda.getDate();

					try {

						if (control.update(produto, descricao, talhao, lote, tipo, qualidade, quantidade, precoKg,
								desconto, valorTotal, cliente, data, selected)) {
							JOptionPane.showMessageDialog(rootPane, "Produto " + produto + " Alterado com Sucesso =) ");

							// CHAMADA DO MÉTODO QUE PREENCHE A TABELA DE PRODUTOS

								//preencheTabela();
								//valorTotal();

								// Limpar os campos após a inserção do novo produto na tabela

								atualizar();
							
						} else {
							JOptionPane.showMessageDialog(rootPane, "Problema ao executar a Alteração!");
						}
					} catch (HeadlessException | SQLException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(rootPane, "Erro!");
					}
				} else {
					JOptionPane.showMessageDialog(rootPane, "Por favor selecione um Produto para Alterar!");
				}
			}
		});

		JButton btnExcluir = new JButton("Apagar");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VenderDAO vDao = new VenderDAO();
				if (table.getSelectedRow() != -1) {
					int op = JOptionPane.showConfirmDialog(null, "Deseja Realmente excluir o Produto selecionado?",
							"Excluir Item", JOptionPane.YES_NO_OPTION);
					if (op != 2 && op != -1) {
						if (op == 0) {

							try {

								vDao.delete(selected);

								DefaultTableModel modelo = (DefaultTableModel) table.getModel();
								modelo.removeRow(selectedRow);

								// Limpar os campos após a inserção do novo produto na tabela
								valorTotal();
								atualizar();
								JOptionPane.showMessageDialog(null, "Produto Excluído!");
							} catch (SQLException ex) {
								// TODO Auto-generated catch block
								ex.printStackTrace();
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(rootPane, "Por favor selecione um Produto para excluir");
				}

			}
		});

		JButton btnAtualizar = new JButton("");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				atualizar();
			}
		});
		btnAtualizar.setIcon(
				new ImageIcon("C:\\Users\\lequi\\OneDrive\\\u00C1rea de Trabalho\\ProjetoPCC\\Images\\atualizar.jpg"));

		JLabel lblData = new JLabel("Data");

		JLabel lblProduto = new JLabel("Produto");

		textProduto = new JTextField();
		textProduto.setColumns(10);

		textDescricao = new JTextField();
		textDescricao.setColumns(10);

		JLabel lblTalhao = new JLabel("Talh\u00E3o");

		textTalhao = new JTextField();
		textTalhao.setColumns(10);

		JLabel lblLote = new JLabel("Lote");

		textLote = new JTextField();
		textLote.setColumns(10);

		JLabel lblTipo = new JLabel("Tipo");

		textTipo = new JTextField();
		textTipo.setColumns(10);

		JLabel lblQualidade = new JLabel("Qualidade");

		textQualidade = new JTextField();
		textQualidade.setColumns(10);

		JLabel lblPreoKg = new JLabel("Pre\u00E7o Kg");

		textPrecoKg = new JTextField();
		textPrecoKg.setColumns(10);

		JLabel lblDesconto = new JLabel("Desconto");

		textDesconto = new JTextField();
		textDesconto.setColumns(10);

		textValorTotal = new JTextField();
		textValorTotal.setColumns(10);

		textCliente = new JTextField();
		textCliente.setColumns(10);

		textQuantidade = new JTextField();
		textQuantidade.setColumns(10);

		JLabel lblValorTotal = new JLabel("Valor Total");

		JLabel lblCliente = new JLabel("Cliente");

		JLabel lblQuantidade = new JLabel("Quantidade");

		textId = new JTextField();
		textId.setEditable(false);
		textId.setEnabled(false);
		textId.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblData).addComponent(lblProduto).addComponent(lblTalhao)
								.addComponent(lblLote).addComponent(lblTipo).addComponent(lblQualidade)
								.addComponent(lblPreoKg).addComponent(lblDesconto).addComponent(lblValorTotal)
								.addComponent(lblCliente).addComponent(lblQuantidade))
						.addGap(18)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(textQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textValorTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textDesconto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textPrecoKg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textQualidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(
										textLote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_2.createSequentialGroup().addComponent(btnAlterar).addGap(18)
										.addComponent(btnExcluir).addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 26,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(textTalhao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
										.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
												.addComponent(dataVenda, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(textId,
														GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
										.addGroup(Alignment.LEADING,
												gl_panel_2.createSequentialGroup()
														.addComponent(textProduto, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(textDescricao, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(66, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(btnNovo)
								.addComponent(btnExcluir).addComponent(btnAlterar)
								.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addComponent(dataVenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblData).addComponent(textId, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(8)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(textProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblProduto))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(textTalhao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTalhao))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(textLote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLote))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(textTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTipo))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(textQualidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQualidade))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(textPrecoKg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPreoKg))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(textDesconto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDesconto))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(textValorTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblValorTotal))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(textCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCliente))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(textQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuantidade))
						.addContainerGap(28, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);

		JLabel lblTotal = new JLabel("Quantidade Total");

		textQuantTotal = new JTextField();
		textQuantTotal.setEditable(false);
		textQuantTotal.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(16).addComponent(lblTotal)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textQuantTotal, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE).addGap(4)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(textQuantTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTotal))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		cbxData.setEnabled(false);
		cbxData.setSelected(true);

		textFiltraProduto = new JTextField();
		textFiltraProduto.setColumns(10);

		textFiltraLote = new JTextField();
		textFiltraLote.setColumns(10);

		textFiltraPreco = new JTextField();
		textFiltraPreco.setColumns(10);

		textFiltraQualidade = new JTextField();
		textFiltraQualidade.setColumns(10);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbxData.isSelected()) {
					FiltrarData(dataInicial.getDate(), dataFinal.getDate());
					valorTotal();
				}
			}
		});

		JLabel lblInicial = new JLabel("Inicial");

		JLabel lblFinal = new JLabel("Final");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(26)
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(cbxLote)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(btnFiltrar)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(cbxData)
												.addComponent(cbxProduto)))
								.addComponent(cbxPreco))
						.addGap(30)).addGroup(gl_panel.createSequentialGroup().addComponent(cbxQualidade).addGap(18)))
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textFiltraLote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(textFiltraProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(dataInicial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblInicial))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblFinal)
										.addComponent(dataFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addComponent(textFiltraPreco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(textFiltraQualidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap(53, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup().addComponent(btnFiltrar).addGap(11))
								.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblInicial).addComponent(lblFinal))
										.addGap(8)))
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(dataFinal, Alignment.LEADING, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxData, Alignment.LEADING)
								.addComponent(dataInicial, Alignment.LEADING, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(8)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(cbxProduto)
								.addComponent(textFiltraProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(cbxLote).addComponent(
								textFiltraLote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFiltraPreco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxPreco))
						.addGap(10)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFiltraQualidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxQualidade))
						.addContainerGap(61, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (table.getSelectedRow() != -1) {
					textId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					dataVenda.setDate((Date) table.getValueAt(table.getSelectedRow(), 1));
					textProduto.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					textDescricao.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					textTalhao.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
					textLote.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
					textTipo.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
					textQualidade.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
					textPrecoKg.setText(table.getValueAt(table.getSelectedRow(), 8).toString());
					textDesconto.setText(table.getValueAt(table.getSelectedRow(), 9).toString());
					textValorTotal.setText(table.getValueAt(table.getSelectedRow(), 10).toString());
					textCliente.setText(table.getValueAt(table.getSelectedRow(), 11).toString());
					textQuantidade.setText(table.getValueAt(table.getSelectedRow(), 12).toString());
				}
			}

		});

		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null, null, null, null, null }, },
				new String[] { "Key", "Data da venda", "Produto", "Descri\u00E7\u00E3o ", "Talh\u00E3o", "Lote", "Tipo",
						"Qualidade", "Pre\u00E7o Kg", "Desconto%", "Valor Total", "Cliente", "Quantidade" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(36);
		table.getColumnModel().getColumn(1).setPreferredWidth(83);
		table.getColumnModel().getColumn(2).setPreferredWidth(48);
		table.getColumnModel().getColumn(3).setPreferredWidth(102);
		table.getColumnModel().getColumn(4).setPreferredWidth(82);
		table.getColumnModel().getColumn(5).setPreferredWidth(62);
		table.getColumnModel().getColumn(6).setPreferredWidth(66);
		table.getColumnModel().getColumn(7).setPreferredWidth(105);
		table.getColumnModel().getColumn(8).setPreferredWidth(63);
		table.getColumnModel().getColumn(9).setPreferredWidth(60);
		table.getColumnModel().getColumn(10).setPreferredWidth(66);
		table.getColumnModel().getColumn(11).setPreferredWidth(97);
		table.getColumnModel().getColumn(12).setPreferredWidth(81);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		//preencheTabela();
		//valorTotal();
	}

	public void preencheTabela() {
		try {
			List<VenderModel> lista = vDao.consultaProdutos();
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			table.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (VenderModel p : lista) {
				Object[] objeto = new Object[13];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getTalhao();
				objeto[5] = p.getLote();
				objeto[6] = p.getTipo();
				objeto[7] = p.getQualidade();
				objeto[8] = p.getPrecoKg();
				objeto[9] = p.getDesconto();
				objeto[10] = p.getValorTotal();
				objeto[11] = p.getCliente();
				objeto[12] = p.getQuantidade();
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

	public void atualizar() {
		//preencheTabela();

		textCliente.setText("");
		textDesconto.setText("");
		textDescricao.setText("");
		textFiltraLote.setText("");
		textFiltraPreco.setText("");
		textFiltraProduto.setText("");
		textFiltraQualidade.setText("");
		textId.setText("");
		textLote.setText("");
		textPrecoKg.setText("");
		textProduto.setText("");
		textQualidade.setText("");
		textQuantidade.setText("");
		textTalhao.setText("");
		textTipo.setText("");
		textValorTotal.setText("");
		cbxLote.setSelected(false);
		cbxPreco.setSelected(false);
		cbxProduto.setSelected(false);
		cbxQualidade.setSelected(false);
	}

	public void FiltrarData(Date dataInicial, Date dataFim) {
		try {
			Date inicial;
			Date fim;
			inicial = new java.sql.Date(dataInicial.getTime());
			fim = new java.sql.Date(dataFim.getTime());
			List<VenderModel> lista = vDao.consultaPorData(inicial, fim);
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			table.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (VenderModel p : lista) {
				Object[] objeto = new Object[13];
				objeto[0] = p.getId();
				objeto[1] = p.getData();
				objeto[2] = p.getProduto();
				objeto[3] = p.getDescricao();
				objeto[4] = p.getTalhao();
				objeto[5] = p.getLote();
				objeto[6] = p.getTipo();
				objeto[7] = p.getQualidade();
				objeto[8] = p.getPrecoKg();
				objeto[9] = p.getDesconto();
				objeto[10] = p.getValorTotal();
				objeto[11] = p.getCliente();
				objeto[12] = p.getQuantidade();
				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void valorTotal() {
		float soma = 0;
		if (table.getRowCount() == 0) {
			textQuantTotal.setText("");
		} else {
			for (int u = 0; u < table.getRowCount(); u++) {
				soma = soma + Float.valueOf(table.getValueAt(u, 12).toString());
				textQuantTotal.setText(String.valueOf(soma));
			}
		}
	}
}
