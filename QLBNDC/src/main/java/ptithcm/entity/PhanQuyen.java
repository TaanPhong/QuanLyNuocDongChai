package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PhanQuyen")
public class PhanQuyen {
	@Id
	@GeneratedValue
	private Integer MAPQ;
	private String Quyen;

	@OneToMany(mappedBy = "phanQuyen", fetch = FetchType.EAGER)
	private Collection<TaiKhoan> taiKhoan;

	public PhanQuyen() {
		super();
	}

	public PhanQuyen(Integer mAPQ, String quyen, Collection<TaiKhoan> taiKhoan) {
		super();
		MAPQ = mAPQ;
		Quyen = quyen;
		this.taiKhoan = taiKhoan;
	}

	public Integer getMAPQ() {
		return MAPQ;
	}

	public void setMAPQ(Integer mAPQ) {
		MAPQ = mAPQ;
	}

	public String getQuyen() {
		return Quyen;
	}

	public void setQuyen(String quyen) {
		Quyen = quyen;
	}

	public Collection<TaiKhoan> getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(Collection<TaiKhoan> taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	@Override
	public String toString() {
		return "PhanQuyen [MAPQ=" + MAPQ + ", Quyen=" + Quyen + ", taiKhoan=" + taiKhoan + "]";
	}

}
