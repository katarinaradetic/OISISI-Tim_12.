package komponente;

import aplikacija.Singleton;
import kontroler.PredstavaKontroler;
import model.Predstava;
import model.Pretraga;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import utils.ButtonEditor;
import utils.ButtonRenderer;
import utils.DateLabelFormatter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ProzorPredstave extends JPanel {

    private JButton btnSortNaziv;
    private boolean sortAscNaziv = true;
    private JButton btnSortDatum;
    private boolean sortAscDatum = true;
    private JButton btnSortCena;
    private boolean sortAscCena = true;
    private JTable tblTabela;
    private JTextField tfPretraga;
    private JButton btnPretraga;
    private JLabel lblDonjiDatum;
    private JLabel lblGornjiDatum;
    private JDatePickerImpl dpDonjiDatum;
    private JDatePickerImpl dpGornjiDatum;
    private JLabel lblDonjaCena;
    private JLabel lblGornjaCena;
    private JSpinner jsDonjaCena;
    private JSpinner jsGornjaCena;
    private Map<Long, Predstava> prikazanePredstave;
    private JLabel lblRezultat;

    public ProzorPredstave() {
        tfPretraga = new JTextField(30);
        lblRezultat = new JLabel("Ne postoji predstava koja odgovara parametrima pretrage");
        lblRezultat.setVisible(false);

        lblDonjaCena = new JLabel("Od: ");
        lblGornjaCena = new JLabel("do: ");
        SpinnerModel donjaCena =
                new SpinnerNumberModel(0.00f, 0.00f, 1000000000f, 0.01f);
        jsDonjaCena = new JSpinner(donjaCena);
        jsDonjaCena.setPreferredSize(new Dimension(202, jsDonjaCena.getPreferredSize().height));
        jsDonjaCena.setMinimumSize(new Dimension(202, jsDonjaCena.getPreferredSize().height));
        SpinnerModel gornjaCena =
                new SpinnerNumberModel(0.00f, 0.00f, 1000000000f, 0.01f);
        jsGornjaCena = new JSpinner(gornjaCena);
        jsGornjaCena.setPreferredSize(new Dimension(202, jsGornjaCena.getPreferredSize().height));
        jsGornjaCena.setMinimumSize(new Dimension(202, jsGornjaCena.getPreferredSize().height));

        lblDonjiDatum = new JLabel("Od: ");
        lblGornjiDatum = new JLabel("do: ");
        UtilDateModel model1 = new UtilDateModel();
        Date d = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        model1.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        model1.setSelected(true);
        UtilDateModel model2 = new UtilDateModel();
        model2.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        model2.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Danas");
        p.put("text.month", "Mesec");
        p.put("text.year", "Godina");
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p);
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p);
        dpDonjiDatum = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
        dpGornjiDatum = new JDatePickerImpl(datePanel2, new DateLabelFormatter());

        btnSortNaziv = new JButton("Sortiraj po nazivu");
        btnSortDatum = new JButton("Sortiraj po datumu");
        btnSortCena = new JButton("Sortiraj po ceni");
        btnPretraga = new JButton("Pretraži");

        btnPretraga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pretraga pretraga = pretrazi();
                PredstavaKontroler predstavaKontroler = Singleton.getInstance().getPredstavaKontroler();
                List<Predstava> rez = predstavaKontroler.pretraziPredstave(pretraga);
                if (rez.isEmpty()) {
                    lblRezultat.setVisible(true);
                    tblTabela.setVisible(false);
                } else {
                    popuniTabelu(rez);
                }
            }
        });

        btnSortNaziv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Predstava> predstave = Singleton.getInstance().getPrikazanePredstave().values()
                        .stream().collect(Collectors.toList());
                if (sortAscNaziv) {
                    List<Predstava> sortiranePredstave = predstave.stream().sorted(
                            Comparator.comparing(Predstava::getNaziv)).collect(Collectors.toList());
                    popuniTabelu(sortiranePredstave);
                    sortAscNaziv = false;
                } else {
                    List<Predstava> sortiranePredstave = predstave.stream().sorted(
                            Comparator.comparing(Predstava::getNaziv).reversed()).collect(Collectors.toList());
                    popuniTabelu(sortiranePredstave);
                    sortAscNaziv = true;
                }
            }
        });

        btnSortDatum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Predstava> predstave = Singleton.getInstance().getPrikazanePredstave().values()
                        .stream().collect(Collectors.toList());
                if (sortAscDatum) {
                    List<Predstava> sortiranePredstave = predstave.stream().sorted(
                            Comparator.comparing(Predstava::getDatumVreme)).collect(Collectors.toList());
                    popuniTabelu(sortiranePredstave);
                    sortAscDatum = false;
                } else {
                    List<Predstava> sortiranePredstave = predstave.stream().sorted(
                            Comparator.comparing(Predstava::getDatumVreme).reversed()).collect(Collectors.toList());
                    popuniTabelu(sortiranePredstave);
                    sortAscDatum = true;
                }
            }
        });

        btnSortCena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Predstava> predstave = Singleton.getInstance().getPrikazanePredstave().values()
                        .stream().collect(Collectors.toList());
                if (sortAscCena) {
                    List<Predstava> sortiranePredstave = predstave.stream().sorted(
                            Comparator.comparing(Predstava::getCena)).collect(Collectors.toList());
                    popuniTabelu(sortiranePredstave);
                    sortAscCena = false;
                } else {
                    List<Predstava> sortiranePredstave = predstave.stream().sorted(
                            Comparator.comparing(Predstava::getCena).reversed()).collect(Collectors.toList());
                    popuniTabelu(sortiranePredstave);
                    sortAscCena = true;
                }
            }
        });

        prikazanePredstave = new HashMap<>();
        String[] kolone = {"Naziv predstave", "Datum izvođenja", "Cena predstave", "Karte rasprodate",
                "Više informacija"};

        List<Predstava> predstave = Singleton.getInstance().getPredstave().values()
                .stream().collect(Collectors.toList());

        Object[][] podaci = new Object[predstave.size()][3];
        for (int i = 0; i < predstave.size(); i++) {
            Predstava predstava = predstave.get(i);
            Object[] o = new Object[] {predstava.getNaziv(), predstava.getDatumVreme(), predstava.getCena(),
                    predstava.getKarteRasprodate()?"Da": "Ne", "Detalji"};
            podaci[i] = o;
            prikazanePredstave.put(new Long(i), predstava);
        }
        Singleton.getInstance().setPrikazanePredstave(prikazanePredstave);

        DefaultTableModel defaultTableModel = new DefaultTableModel(podaci, kolone);
        tblTabela = new JTable(defaultTableModel);
        tblTabela.getColumn("Više informacija").setCellRenderer(new ButtonRenderer());
        tblTabela.getColumn("Više informacija").setCellEditor(new ButtonEditor(new JCheckBox()));
        tblTabela.setRowHeight(30);
        TableColumnModel columnModel = tblTabela.getColumnModel();

        Enumeration<TableColumn> tcs = columnModel.getColumns();
        while (tcs.hasMoreElements()) {
            TableColumn tc = tcs.nextElement();
            tc.setPreferredWidth(100);
        }
        columnModel.getColumn(0).setPreferredWidth(200);
        columnModel.getColumn(1).setPreferredWidth(200);

        JPanel sortOpcije = new JPanel();
        sortOpcije.setLayout(new GridBagLayout());
        sortOpcije.add(btnSortNaziv, new GridBagConstraints(0, 0, 1, 1, 0,
                0, GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(5, 5, 5,5 ), 0, 0));
        sortOpcije.add(btnSortDatum, new GridBagConstraints(1, 0, 1, 1, 0,
                0, GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(5, 5, 5,5 ), 0, 0));
        sortOpcije.add(btnSortCena, new GridBagConstraints(2, 0, 1, 1, 0,
                0, GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(5, 5, 5,5 ), 0, 0));

        JPanel pnlPretraga = new JPanel();
        pnlPretraga.setLayout(new GridBagLayout());
        pnlPretraga.add(tfPretraga, new GridBagConstraints(0, 0, 1, 1, 0,
                0, GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(50, 5, 0,5 ), 0, 0));
        pnlPretraga.add(btnPretraga, new GridBagConstraints(1, 0, 1, 1, 0,
                0, GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(50, 5, 0,5 ), 0, 0));

        JPanel pnlFilter = new JPanel();
        pnlFilter.setLayout(new GridBagLayout());
        pnlFilter.add(lblDonjiDatum, new GridBagConstraints(0, 0, 1, 1, 0,
                0, GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5,5 ), 0, 0));
        pnlFilter.add(dpDonjiDatum, new GridBagConstraints(1, 0, 1, 1, 0,
                0, GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5,5 ), 0, 0));
        pnlFilter.add(lblGornjiDatum, new GridBagConstraints(2, 0, 1, 1, 0,
                0, GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5,5 ), 0, 0));
        pnlFilter.add(dpGornjiDatum, new GridBagConstraints(3, 0, 1, 1, 0,
                0, GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5,5 ), 0, 0));
        pnlFilter.add(lblDonjaCena, new GridBagConstraints(0, 1, 1, 1, 0,
                0, GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5,5 ), 0, 0));
        pnlFilter.add(jsDonjaCena, new GridBagConstraints(1, 1, 1, 1, 0,
                0, GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5,5 ), 0, 0));
        pnlFilter.add(lblGornjaCena, new GridBagConstraints(2, 1, 1, 1, 0,
                0, GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5,5 ), 0, 0));
        pnlFilter.add(jsGornjaCena, new GridBagConstraints(3, 1, 1, 1, 0,
                0, GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5,5 ), 0, 0));

        setLayout(new GridBagLayout());
        add(pnlPretraga, new GridBagConstraints(0, 0, 3, 1, 0,
                0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(25, 5, 0,5 ), 0, 0));
        add(pnlFilter, new GridBagConstraints(0, 1, 3, 1, 0,
                0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(5, 5, 5,5 ), 0, 0));
        add(sortOpcije, new GridBagConstraints(0, 2, 3, 1, 0,
                0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(50, 5, 0,5 ), 0, 0));
        add(tblTabela.getTableHeader(), new GridBagConstraints(0, 3, 3, 1, 0,
                0, GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(15, 5, 0,5 ), 0, 0));
        add(tblTabela, new GridBagConstraints(0, 4, 3, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 5,5 ),
                0, 0));
        add(lblRezultat, new GridBagConstraints(0, 5, 3, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5,5 ),
                0, 0));
    }

    public void popuniTabelu(List<Predstava> predstave) {
        tblTabela.setVisible(true);
        lblRezultat.setVisible(false);
        prikazanePredstave = new HashMap<>();
        DefaultTableModel tableModel = (DefaultTableModel) tblTabela.getModel();

        String[] kolone = {"Naziv predstave", "Datum izvođenja", "Cena predstave", "Karte rasprodate", "Više informacija"};
//        List<Predstava> predstave = Singleton.getInstance().getPredstave().values()
//                .stream().collect(Collectors.toList());

        Object[][] podaci = new Object[predstave.size()][3];
        for (int i = 0; i < predstave.size(); i++) {
            Predstava predstava = predstave.get(i);
            Object[] o = new Object[] {predstava.getNaziv(), predstava.getDatumVreme(), predstava.getCena(),
                    predstava.getKarteRasprodate()?"Da": "Ne", "Detalji"};
            podaci[i] = o;
            prikazanePredstave.put(new Long(i), predstava);
        }
        Singleton.getInstance().setPrikazanePredstave(prikazanePredstave);

        tableModel.setDataVector(podaci, kolone);
        tblTabela.getColumn("Više informacija").setCellRenderer(new ButtonRenderer());
        tblTabela.getColumn("Više informacija").setCellEditor(new ButtonEditor(new JCheckBox()));
        tblTabela.setRowHeight(30);
        TableColumnModel columnModel = tblTabela.getColumnModel();

        Enumeration<TableColumn> tcs = columnModel.getColumns();
        while (tcs.hasMoreElements()) {
            TableColumn tc = tcs.nextElement();
            tc.setPreferredWidth(100);
        }
        columnModel.getColumn(0).setPreferredWidth(200);
        columnModel.getColumn(1).setPreferredWidth(200);
    }

    public void ocistiPretragu() {
        this.tfPretraga.setText("");
        this.jsDonjaCena.setValue(0.00d);
        this.jsGornjaCena.setValue(0.00d);
        Date d = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        this.dpDonjiDatum.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        this.dpGornjiDatum.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    public Pretraga pretrazi() {
        String naziv = this.tfPretraga.getText();
        Float donjaCena = (float)((double) this.jsDonjaCena.getValue());
        Float gornjaCena = (float)((double) this.jsGornjaCena.getValue());
        Date pocetniDatum = (Date) this.dpDonjiDatum.getModel().getValue();
        Date krajnjiDatum = (Date) this.dpGornjiDatum.getModel().getValue();

        Pretraga pretraga = new Pretraga(naziv, pocetniDatum, krajnjiDatum, donjaCena, gornjaCena);

        return pretraga;
    }
}
