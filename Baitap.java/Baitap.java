// Lớp cơ sở cho tất cả nhân vật
abstract class NhanVat {
    protected String ten;
    protected int mau;
    protected int satThuongCoBan;
    
    public NhanVat(String ten, int mau, int satThuongCoBan) {
        this.ten = ten;
        this.mau = mau;
        this.satThuongCoBan = satThuongCoBan;
    }
    
    // Phương thức trừu tượng – các lớp con bắt buộc phải triển khai
    public abstract void tanCong();
    
    // Getters
    public String layTen() {
        return ten;
    }
    
    public int layMau() {
        return mau;
    }
    
    public int laySatThuongCoBan() {
        return satThuongCoBan;
    }
}

// Interface cho khả năng tấn công tầm xa
interface TanCongTamXa {
    void banTen();
}

// Interface cho khả năng dùng phép thuật
interface TanCongPhepThuat {
    void niemPhap();
}

// ChienBinh
class ChienBinh extends NhanVat {
    public ChienBinh(String ten, int mau, int satThuongCoBan) {
        super(ten, mau, satThuongCoBan);
    }
    
    @Override
    public void tanCong() {
        System.out.println(ten + " (Chiến Binh) tấn công bằng kiếm!");
    }
}

// CungThu
class CungThu extends NhanVat implements TanCongTamXa {
    public CungThu(String ten, int mau, int satThuongCoBan) {
        super(ten, mau, satThuongCoBan);
    }
    
    @Override
    public void tanCong() {
        banTen();
    }
    
    @Override
    public void banTen() {
        System.out.println(ten + " (Cung Thủ) bắn một mũi tên!");
    }
}

// PhapSu
class PhapSu extends NhanVat implements TanCongPhepThuat {
    public PhapSu(String ten, int mau, int satThuongCoBan) {
        super(ten, mau, satThuongCoBan);
    }
    
    @Override
    public void tanCong() {
        niemPhap();
    }
    
    @Override
    public void niemPhap() {
        System.out.println(ten + " (Pháp Sư) niệm phép cầu lửa!");
    }
}

// CungThuPhep – kết hợp cả hai khả năng
class CungThuPhep extends NhanVat implements TanCongTamXa, TanCongPhepThuat {
    public CungThuPhep(String ten, int mau, int satThuongCoBan) {
        super(ten, mau, satThuongCoBan);
    }
    
    @Override
    public void tanCong() {
        banTen();
        niemPhap();
    }
    
    @Override
    public void banTen() {
        System.out.println(ten + " (Cung Thủ Phép) bắn một mũi tên!");
    }
    
    @Override
    public void niemPhap() {
        System.out.println(ten + " đồng thời niệm phép thuật!");
    }
}

// Lớp chính
public class Baitap {
    public static void main(String[] args) {
        // Tạo danh sách nhân vật
        NhanVat[] danhSachNhanVat = new NhanVat[4];
        
        danhSachNhanVat[0] = new ChienBinh("Kanie", 150, 25);
        danhSachNhanVat[1] = new CungThu("Yonrs", 100, 20);
        danhSachNhanVat[2] = new PhapSu("Verra", 80, 30);
        danhSachNhanVat[3] = new CungThuPhep("Bonnie", 110, 22);
        
        // Cho tất cả nhân vật thực hiện hành động tấn công
        System.out.println("=== Trận Chiến Bắt Đầu ===");
        for (NhanVat nv : danhSachNhanVat) {
            nv.tanCong();
        }
        System.out.println("=== Trận Chiến Kết Thúc ===");
    }
}