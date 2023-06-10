package olshop_billing_systems;
import javax.swing.table.DefaultTableModel;
public final class Simpan { 
    static float cost;
    private DefaultTableModel tabel = new DefaultTableModel();
    public Simpan(){
        getTabel().addColumn("Kode Barang");
        getTabel().addColumn("Nama Sepatu");
        getTabel().addColumn("Merk Sepatu");
        getTabel().addColumn("Ukuran Sepatu");
        getTabel().addColumn("Warna");
        getTabel().addColumn("Jumlah");
        getTabel().addColumn("Harga");    
    }
    public DefaultTableModel getTabel(){
        return tabel;
    }
    public void setTabel(DefaultTableModel tabel){
        this.tabel=tabel;
    }
    public double hitung(int price,int jumlah){
        cost = (float) Math.ceil(price*jumlah);
        return cost;}
    public double hitung(double price,int jumlah){
        cost = (float) Math.ceil(price*jumlah);
        return cost;}

    float hitung(float price, double jumlah) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
