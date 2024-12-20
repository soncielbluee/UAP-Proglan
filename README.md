# Pemesanan Tiket Konser

## Deskripsi Program
Program ini adalah sebuah aplikasi berbasis Java dengan antarmuka grafis (GUI) untuk melakukan pemesanan tiket konser. Program ini menggunakan pustaka `Swing` untuk membuat antarmuka pengguna, dan mendukung berbagai fitur seperti menambah, memperbarui, dan menghapus data pemesanan tiket.

### Fitur Utama
- **Input Data Pemesan:**
    - Nama Lengkap
    - Jumlah Tiket
    - Email
    - Pilihan Konser
    - Metode Pembayaran
- **Tabel Pemesanan:**
    - Menampilkan daftar pemesanan yang telah dilakukan.
    - Mendukung aksi untuk memperbarui dan menghapus data.
- **Metode Pembayaran:**
    - QRIS (dengan simulasi dialog QRIS).
    - Transfer Bank.
- **Penghitungan Harga Otomatis:**
    - Harga tiket dihitung berdasarkan jenis konser yang dipilih.

### Teknologi yang Digunakan
- **Bahasa Pemrograman:** Java
- **GUI Library:** Swing
- **IDE:** IntelliJ IDEA atau Visual Studio Code (sesuai preferensi pengguna).

## Struktur Program
Program terdiri dari elemen-elemen berikut:

1. **Frame Utama:**
    - Frame berjudul "Pemesanan Tiket Konser" yang menjadi wadah utama untuk antarmuka pengguna.

2. **Panel Input:**
    - Digunakan untuk memasukkan data pemesan tiket.
    - Berisi formulir dengan label, text field, dan combo box untuk memilih konser serta metode pembayaran.

3. **Tabel Pemesanan:**
    - Menggunakan `JTable` untuk menampilkan daftar pemesanan.
    - Data dikelola dengan `DefaultTableModel`.

4. **Dialog QRIS:**
    - Jika pengguna memilih metode pembayaran QRIS, dialog akan muncul untuk mensimulasikan proses pembayaran dengan QRIS.

5. **Fungsi Penghitungan Harga:**
    - `calculatePrice(String concert, int tickets)` digunakan untuk menghitung total harga berdasarkan konser yang dipilih dan jumlah tiket.

## Cara Menggunakan
1. Jalankan program dari IDE seperti IntelliJ IDEA atau Visual Studio Code.
2. Masukkan data pemesan (nama, jumlah tiket, email, konser, dan metode pembayaran).
3. Klik tombol **Tambah** untuk menambahkan data ke tabel pemesanan.
4. Jika perlu, pilih data di tabel dan gunakan tombol **Update** untuk memperbarui data atau tombol **Hapus** untuk menghapus data.
5. Untuk metode pembayaran QRIS, dialog akan muncul untuk simulasi pemindaian kode QR.

## Struktur Harga
- **Seventeen 'Right Here':** Rp150.000 per tiket.
- **NCT Dream 'The Dream Show 4':** Rp200.000 per tiket.
- **Treasure 'Reeboot':** Rp180.000 per tiket.

## Screenshot Program
Tambahkan screenshot dari program berjalan untuk memberikan gambaran visual kepada pengguna.

## Lisensi
Program ini dibuat untuk tujuan edukasi dan dapat digunakan secara bebas. Jika Anda mengembangkan program ini lebih lanjut, harap berikan atribusi kepada pengembang asli.

---

**Catatan:** Program ini hanya untuk simulasi. Proses pembayaran sebenarnya tidak dilakukan.

