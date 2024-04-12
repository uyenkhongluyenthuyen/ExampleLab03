package com.example.examplelab03;


//1. Thông tin Thí sinh gồm: Số báo danh: string, Họ tên: string, Điểm toán, lý, hóa: float/double.
// Xây dựng class ThiSinh mô tả cấu trúc thông tin này
//2. Xây dựng method tính tổng điểm, điểm trung bình của sinh viên
public class ThiSinh {
    private String sbd;

    private String hoTen;

    private float toan, ly, hoa;

    public ThiSinh(String sbd, String hoTen, float toan, float ly, float hoa) {
        this.sbd = sbd;
        this.hoTen = hoTen;
        this.toan = toan;
        this.ly = ly;
        this.hoa = hoa;
    }


    public String getSbd() {
        return sbd;
    }

    public void setSbd(String sbd) {
        this.sbd = sbd;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public float getToan() {
        return toan;
    }

    public void setToan(float toan) {
        this.toan = toan;
    }

    public float getLy() {
        return ly;
    }

    public void setLy(float ly) {
        this.ly = ly;
    }

    public float getHoa() {
        return hoa;
    }

    public void setHoa(float hoa) {
        this.hoa = hoa;
    }

    public float tongDiem(){
        return toan + ly + hoa;
   }

   public float diemTBC(){
        return (float) (toan + ly + hoa)/3;
   }
}
