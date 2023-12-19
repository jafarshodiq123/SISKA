-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 18, 2023 at 08:12 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `apotek_1`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `autoGenerateLaporan` ()   BEGIN
    DECLARE bulanIni INT;
    DECLARE tahunIni INT;
    DECLARE totalPendapatan INT;
    DECLARE totalPengeluaran INT;
    DECLARE labaBersih INT;
    DECLARE bulanTahun char(7);

    SET bulanIni = MONTH(CURRENT_DATE);
    SET tahunIni = YEAR(CURRENT_DATE);

    SET bulanTahun = CONCAT(tahunIni, '-', LPAD(bulanIni, 2, '0'));

    SELECT COALESCE(SUM(total_harga), 0) INTO totalPendapatan FROM transaksi_penjualan WHERE YEAR(tanggal_transaksi) = tahunIni AND MONTH(tanggal_transaksi) = bulanIni;
    
    SELECT COALESCE(SUM(total_pengeluaran), 0) INTO totalPengeluaran FROM pengeluaran WHERE YEAR(tanggal_pengeluaran) = tahunIni AND MONTH(tanggal_pengeluaran) = bulanIni;

    SET labaBersih = totalPendapatan - totalPengeluaran;

    IF labaBersih >= 0 THEN
        INSERT INTO laporan (pendapatan, pengeluaran, laba_bersih, rugi_bersih, bulan_tahun, status)
        VALUES (totalPendapatan, totalPengeluaran, labaBersih, 0, bulanTahun, 'Laba Bersih');
    ELSE
        INSERT INTO laporan (pendapatan, pengeluaran, laba_bersih, rugi_bersih, bulan_tahun, status)
        VALUES (totalPendapatan, totalPengeluaran, 0, labaBersih, bulanTahun, 'Rugi Bersih');
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `simpanDataObat` (IN `p_kode_obat` VARCHAR(14), IN `p_nama_obat` VARCHAR(50), IN `p_id_bentuk_sediaan` INT, IN `p_id_kategori` INT, IN `p_aturan_pakai` VARCHAR(70))   BEGIN
    -- Cek apakah obat dengan kode_obat sudah ada
    DECLARE existing_obat INT;
    SELECT COUNT(*) INTO existing_obat FROM obat WHERE kode_obat = p_kode_obat;

    IF existing_obat > 0 THEN
        -- Obat sudah ada, lakukan update
        UPDATE obat
        SET
            nama_obat = p_nama_obat,
            id_bentuk_sediaan = p_id_bentuk_sediaan,
            id_kategori = p_id_kategori,
            aturan_pakai = p_aturan_pakai
        WHERE kode_obat = p_kode_obat;
    ELSE
        -- Obat belum ada, lakukan insert
        INSERT INTO obat (kode_obat, nama_obat, id_bentuk_sediaan, id_kategori,aturan_pakai)
        VALUES (p_kode_obat, p_nama_obat, p_id_bentuk_sediaan, p_id_kategori, p_aturan_pakai);
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `simpanJenisPenjualan` (IN `kodeObat` CHAR(14), IN `total` INT, IN `harga` INT, IN `idBentukSatuan` INT)   BEGIN
DECLARE idSatuanDefault int ;

insert into jenis_penjualan VALUES(null,kodeObat,total,harga,idBentukSatuan);
END$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `GetForeignKeyCountById` (`tableName` VARCHAR(255), `columnName` VARCHAR(255), `targetId` INT) RETURNS INT(11) DETERMINISTIC BEGIN
    DECLARE countData INT;

    -- Menghitung jumlah data
    SELECT COUNT(*) INTO countData
    FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
    WHERE REFERENCED_TABLE_NAME = tableName
      AND REFERENCED_COLUMN_NAME = columnName
      AND COLUMN_NAME = targetId;

    -- Mengembalikan hasil
    RETURN countData;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `total_obat` () RETURNS INT(11) DETERMINISTIC BEGIN
DECLARE totalObat int DEFAULT 0 ;
SELECT COUNT(*) INTO totalObat FROM obat WHERE jumlah_obat > 0;
RETURN totalObat;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `total_pendapatan_harian` (`tanggal` TIMESTAMP) RETURNS INT(11) DETERMINISTIC BEGIN
DECLARE total_harga_all INT(10) DEFAULT 0;
SELECT SUM(total_harga)  INTO total_harga_all from transaksi_penjualan WHERE DATE(tanggal_transaksi) = tanggal;
RETURN total_harga_all;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `total_pengeluaran_bulanan` (`tanggal` DATE) RETURNS INT(11) DETERMINISTIC BEGIN
DECLARE total_pengeluaran_all INT DEFAULT 0 ;
SELECT sum(total_pengeluaran) INTO total_pengeluaran_all FROM pengeluaran WHERE MONTH(tanggal_pengeluaran) = MONTH(tanggal);
RETURN total_pengeluaran_all;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `bentuk_sediaan_obat`
--

CREATE TABLE `bentuk_sediaan_obat` (
  `id` int(11) NOT NULL,
  `nama_bentuk_sediaan` varchar(30) NOT NULL,
  `deskripsi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bentuk_sediaan_obat`
--

INSERT INTO `bentuk_sediaan_obat` (`id`, `nama_bentuk_sediaan`, `deskripsi`) VALUES
(1, 'BOTOL', 'Wadah obat yang terbuat dari kaca atau plastik. Botol biasanya digunakan untuk obat sirup, tablet, kaplet, dan obat lainnya.'),
(2, 'BOX', 'Kemasan obat yang terbuat dari karton. Box biasanya digunakan untuk obat sirup, tablet, kaplet, dan obat lainnya.'),
(3, 'SCT', 'Kemasan obat yang terbuat dari selembar kertas tipis. SCT biasanya digunakan untuk obat tablet.'),
(4, 'STRIP', 'Kemasan obat yang terbuat dari selembar kertas tipis. Strip biasanya digunakan untuk obat tablet.'),
(6, 'TABLET', 'Bentuk sediaan obat padat yang biasanya berbentuk bulat, pipih, dan mengandung zat aktif yang terdistribusi secara homogen. Tablet dapat ditelan langsung, dikunyah, atau dihancurkan terlebih dahulu.'),
(7, 'PACK', 'Kemasan obat yang terdiri dari beberapa strip atau beberapa tablet.');

-- --------------------------------------------------------

--
-- Stand-in structure for view `data_jenis_penjualan`
-- (See below for the actual view)
--
CREATE TABLE `data_jenis_penjualan` (
`kode_obat` char(14)
,`nama_obat` varchar(50)
,`satuan` varchar(30)
,`total` int(11)
,`harga` int(11)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `data_obat`
-- (See below for the actual view)
--
CREATE TABLE `data_obat` (
`kode_obat` char(14)
,`nama_obat` varchar(50)
,`jumlah_obat` int(11)
,`nama_kategori` varchar(50)
,`satuan` varchar(30)
,`tanggal_dibuat` timestamp
,`aturan_pakai` varchar(50)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `data_stok_obat`
-- (See below for the actual view)
--
CREATE TABLE `data_stok_obat` (
`id` int(11)
,`kode_obat` char(14)
,`nama_obat` varchar(50)
,`jumlah_obat` int(11)
,`harga_beli` int(11)
,`tanggal_kadaluarsa` date
,`tanggal_masuk` date
,`status_kadaluarsa` int(1)
);

-- --------------------------------------------------------

--
-- Table structure for table `detail_pembelian`
--

CREATE TABLE `detail_pembelian` (
  `id` int(11) NOT NULL,
  `kode_transaksi` char(17) NOT NULL,
  `kode_obat` char(14) NOT NULL,
  `harga` int(11) NOT NULL DEFAULT 0,
  `qty` int(11) NOT NULL DEFAULT 0,
  `subtotal` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_pembelian`
--

INSERT INTO `detail_pembelian` (`id`, `kode_transaksi`, `kode_obat`, `harga`, `qty`, `subtotal`) VALUES
(8, 'TRXP_031223213016', 'OB031223183802', 4000, 50, 200000),
(9, 'TRXP_031223213643', 'OB031223183802', 5000, 50, 250000),
(10, 'TRXP_031223213643', 'OB031223211134', 7000, 50, 350000),
(11, 'TRXP_111223173749', 'OB031223211134', 10, 1000, 10000),
(12, 'TRXP_111223202219', 'OB031223183802', 2000, 1, 2000),
(13, 'TRXP_111223202406', 'OB031223183802', 1, 1, 1),
(14, 'TRXP_111223202721', 'OB031223183802', 1, 1, 1),
(15, 'TRXP_181223122041', 'OB031223183802', 2000, 1, 2000);

-- --------------------------------------------------------

--
-- Table structure for table `detail_penjualan`
--

CREATE TABLE `detail_penjualan` (
  `id` int(11) NOT NULL,
  `kode_transaksi` char(17) NOT NULL,
  `kode_obat` char(14) NOT NULL,
  `harga` int(11) NOT NULL DEFAULT 0,
  `qty` int(11) NOT NULL DEFAULT 0,
  `subtotal` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_penjualan`
--

INSERT INTO `detail_penjualan` (`id`, `kode_transaksi`, `kode_obat`, `harga`, `qty`, `subtotal`) VALUES
(8, 'TRXJ_031223213517', 'OB031223183802', 100000, 50, 500000),
(9, 'TRXJ_031223214413', 'OB031223183802', 11000, 3, 33000),
(10, 'TRXJ_031223214413', 'OB031223211134', 7083, 1, 7083),
(11, 'TRXJ_051223140228', 'OB031223183802', 11000, 1, 11000),
(12, 'TRXJ_081223165442', 'OB031223183802', 11000, 1, 11000),
(13, 'TRXJ_081223165828', 'OB031223183802', 11000, 1, 11000),
(14, 'TRXJ_081223171414', 'OB031223183802', 11000, 1, 11000),
(15, 'TRXJ_081223221431', 'OB031223183802', 11000, 1, 11000),
(16, 'TRXJ_091223241653', 'OB031223183802', 11000, 1, 11000),
(17, 'TRXJ_091223102131', 'OB031223183802', 100000, 10, 100000),
(18, 'TRXJ_091223112842', 'OB031223183802', 100000, 30, 300000),
(19, 'TRXJ_101223021935', 'OB031223183802', 11000, 1, 11000),
(20, 'TRXJ_101223113826', 'OB031223211134', 11000, 39, 429000),
(21, 'TRXJ_101223120942', 'OB031223211134', 11000, 1, 11000),
(22, 'TRXJ_101223132318', 'OB031223211134', 11000, 1, 11000),
(23, 'TRXJ_101223132336', 'OB031223211134', 11000, 1, 11000),
(24, 'TRXJ_101223175934', 'OB031223211134', 11000, 1, 11000),
(25, 'TRXJ_101223234824', 'OB031223211134', 11000, 15, 165000),
(26, 'TRX109846ef', 'OB031223211134', 11000, 2, 22000),
(27, 'TRX109846ef', 'OB031223183802', 11000, 1, 11000),
(28, 'TRX0f1c80e9', 'OB031223183802', 11000, 1, 11000),
(29, 'TRXa2d8a54b', 'OB031223183802', 11000, 1, 11000);

--
-- Triggers `detail_penjualan`
--
DELIMITER $$
CREATE TRIGGER `penguran stok obat` AFTER INSERT ON `detail_penjualan` FOR EACH ROW UPDATE obat SET  jumlah_obat =jumlah_obat- new.qty WHERE obat.kode_obat = new.kode_obat
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `jenis_penjualan`
--

CREATE TABLE `jenis_penjualan` (
  `id` int(11) NOT NULL,
  `kode_obat` char(14) NOT NULL,
  `total` int(11) NOT NULL DEFAULT 0,
  `harga` int(11) NOT NULL DEFAULT 0,
  `id_bentuk_sediaan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jenis_penjualan`
--

INSERT INTO `jenis_penjualan` (`id`, `kode_obat`, `total`, `harga`, `id_bentuk_sediaan`) VALUES
(50, 'OB031223211134', 1, 11000, 4),
(68, 'OB031223183802', 1, 11000, 3),
(69, 'OB031223183802', 10, 100000, 7);

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `id` int(11) NOT NULL,
  `nama_kategori` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id`, `nama_kategori`) VALUES
(1, 'Obat keras'),
(2, 'Obat ringan'),
(3, 'Obat resep'),
(6, 'Obat kering');

-- --------------------------------------------------------

--
-- Table structure for table `laporan`
--

CREATE TABLE `laporan` (
  `id` int(11) NOT NULL,
  `pendapatan` int(11) NOT NULL DEFAULT 0,
  `pengeluaran` int(11) NOT NULL DEFAULT 0,
  `laba_bersih` int(11) NOT NULL DEFAULT 0,
  `rugi_bersih` int(11) NOT NULL DEFAULT 0,
  `bulan_tahun` char(7) NOT NULL,
  `status` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `laporan`
--

INSERT INTO `laporan` (`id`, `pendapatan`, `pengeluaran`, `laba_bersih`, `rugi_bersih`, `bulan_tahun`, `status`) VALUES
(1, 1732083, 1400000, 332083, 0, '2023-12', 'Laba Bersih');

-- --------------------------------------------------------

--
-- Stand-in structure for view `laporan_pembelian`
-- (See below for the actual view)
--
CREATE TABLE `laporan_pembelian` (
`kode_transaksi` char(17)
,`nama_suplier` varchar(50)
,`jumlah_obat` decimal(32,0)
,`total_harga` int(11)
,`tanggal_transaksi` timestamp
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `laporan_pendapatan`
-- (See below for the actual view)
--
CREATE TABLE `laporan_pendapatan` (
`pendapatan` decimal(32,0)
,`tanggal` varchar(10)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `laporan_pengeluaran`
-- (See below for the actual view)
--
CREATE TABLE `laporan_pengeluaran` (
`id` int(11)
,`nama` varchar(40)
,`keterangan` varchar(70)
,`jumlah_pengeluaran` int(11)
,`tanggal_pengeluaran` date
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `laporan_penjualan`
-- (See below for the actual view)
--
CREATE TABLE `laporan_penjualan` (
`nama_pengguna` varchar(40)
,`kode_transaksi` char(17)
,`total_obat` decimal(32,0)
,`total_harga` int(11)
,`pembayaran` int(11)
,`kembalian` int(11)
,`tanggal_transaksi` timestamp
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `laporan_stok`
-- (See below for the actual view)
--
CREATE TABLE `laporan_stok` (
`kode_obat` char(14)
,`nama_obat` varchar(50)
,`obat_terjual` int(11)
,`tanggal_transaksi` timestamp
);

-- --------------------------------------------------------

--
-- Table structure for table `obat`
--

CREATE TABLE `obat` (
  `kode_obat` char(14) NOT NULL,
  `id_kategori` int(11) NOT NULL,
  `nama_obat` varchar(50) NOT NULL,
  `id_bentuk_sediaan` int(11) NOT NULL,
  `jumlah_obat` int(11) NOT NULL DEFAULT 0,
  `aturan_pakai` varchar(50) DEFAULT NULL,
  `tanggal_dibuat` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `obat`
--

INSERT INTO `obat` (`kode_obat`, `id_kategori`, `nama_obat`, `id_bentuk_sediaan`, `jumlah_obat`, `aturan_pakai`, `tanggal_dibuat`) VALUES
('OB031223183802', 2, 'ANDALAN TES KEHAMILAN COMPACT', 3, 101, ' -', '2023-12-03 14:07:48'),
('OB031223211134', 2, 'ANDALAN TAB', 4, 1083, '-', '2023-12-03 14:11:34');

-- --------------------------------------------------------

--
-- Table structure for table `pengeluaran`
--

CREATE TABLE `pengeluaran` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `total_pengeluaran` int(11) NOT NULL DEFAULT 0,
  `keterangan` varchar(70) NOT NULL,
  `tanggal_pengeluaran` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pengeluaran`
--

INSERT INTO `pengeluaran` (`id`, `id_user`, `total_pengeluaran`, `keterangan`, `tanggal_pengeluaran`) VALUES
(1, 8, 150000, 'Air', '2023-12-03'),
(2, 8, 1200000, 'Karyawan', '2023-12-03'),
(3, 1, 30000, 'mebeli kentang ', '2023-12-11'),
(4, 1, 20000, 'testeing', '2023-12-11');

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `id` int(11) NOT NULL,
  `kode_obat` char(14) NOT NULL,
  `kode_suplier` char(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`id`, `kode_obat`, `kode_suplier`) VALUES
(1, 'OB031223183802', 'SP_031223125633'),
(36, 'OB031223183802', 'SUP2400a309'),
(63, 'OB031223211134', 'SP_031223125807'),
(73, 'OB031223211134', 'SUP05357d7e'),
(74, 'OB031223183802', 'SUP05357d7e'),
(76, 'OB031223183802', 'SUP7af1a0c0'),
(89, 'OB031223183802', 'SUP60f86793'),
(90, 'OB031223211134', 'SUP60f86793');

-- --------------------------------------------------------

--
-- Stand-in structure for view `printerview`
-- (See below for the actual view)
--
CREATE TABLE `printerview` (
`kode_transaksi` char(17)
,`username` varchar(50)
,`tanggal_transaksi` timestamp
,`nama_obat` varchar(50)
,`qty` int(11)
,`harga` int(11)
,`subtotal` int(11)
,`total_harga` int(11)
,`pembayaran` int(11)
,`kembalian` int(11)
);

-- --------------------------------------------------------

--
-- Table structure for table `shift`
--

CREATE TABLE `shift` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `saldo_awal_kas` int(11) NOT NULL,
  `saldo_akhir_kas` int(11) NOT NULL DEFAULT 0,
  `waktu_buka` time NOT NULL,
  `waktu_tutup` time DEFAULT NULL,
  `total_penjualan` int(11) NOT NULL DEFAULT 0,
  `total_pembayaran` int(11) NOT NULL DEFAULT 0,
  `tanggal_dibuat` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `shift`
--

INSERT INTO `shift` (`id`, `id_user`, `saldo_awal_kas`, `saldo_akhir_kas`, `waktu_buka`, `waktu_tutup`, `total_penjualan`, `total_pembayaran`, `tanggal_dibuat`) VALUES
(12, 1, 90000, 270000, '16:31:28', '17:25:28', 1, 180000, '2023-12-03 09:31:28');

-- --------------------------------------------------------

--
-- Table structure for table `stok_obat`
--

CREATE TABLE `stok_obat` (
  `id` int(11) NOT NULL,
  `kode_obat` char(14) NOT NULL,
  `kode_suplier` char(16) NOT NULL,
  `jumlah_obat` int(11) NOT NULL DEFAULT 0,
  `harga_beli` int(11) NOT NULL DEFAULT 0,
  `tanggal_kadaluarsa` date NOT NULL,
  `tanggal_masuk` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `stok_obat`
--

INSERT INTO `stok_obat` (`id`, `kode_obat`, `kode_suplier`, `jumlah_obat`, `harga_beli`, `tanggal_kadaluarsa`, `tanggal_masuk`) VALUES
(8, 'OB031223183802', 'SP_031223125633', 100, 4000, '2023-11-01', '2023-12-03'),
(9, 'OB031223183802', 'SP_031223125807', 0, 5000, '2023-12-12', '2023-12-03'),
(10, 'OB031223211134', 'SP_031223125807', 83, 7000, '2023-12-14', '2023-12-03'),
(12, 'OB031223211134', 'SP_031223125807', 1000, 10, '2023-12-28', '2023-12-11'),
(13, 'OB031223183802', 'SP_031223125633', 0, 2000, '2023-12-21', '2023-12-11'),
(14, 'OB031223183802', 'SP_031223125633', 0, 1, '2023-12-29', '2023-12-11'),
(15, 'OB031223183802', 'SP_031223125633', 1, 1, '2023-12-29', '2023-12-11'),
(16, 'OB031223183802', 'SUP2400a309', 1, 2000, '2023-12-22', '2023-12-18');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `kode_suplier` char(16) NOT NULL,
  `nama_suplier` varchar(50) NOT NULL,
  `alamat` varchar(70) NOT NULL,
  `nomor_telepon` char(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`kode_suplier`, `nama_suplier`, `alamat`, `nomor_telepon`) VALUES
('SP_031223125633', 'PT Sejahtera', 'Situbondo, Indonesia', '087672343768'),
('SP_031223125807', 'PT Merdeka jaya', 'Paiton, probolinggok', '081237382734'),
('SUP05357d7e', 'PT BLUSUK BIRU', 'CERME,BONDOWOSO', '081238382834'),
('SUP2400a309', 'PT BARU BUKA', 'JL WAHID', '08123123'),
('SUP60f86793', 'PT ABADI JAWA', 'jl merdeka', '081238382834'),
('SUP7af1a0c0', 'PT ADMAJAYA ', '2', '0');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_pembelian`
--

CREATE TABLE `transaksi_pembelian` (
  `kode_transaksi` char(17) NOT NULL,
  `kode_suplier` char(16) NOT NULL,
  `total_harga` int(11) NOT NULL DEFAULT 0,
  `tanggal_transaksi` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi_pembelian`
--

INSERT INTO `transaksi_pembelian` (`kode_transaksi`, `kode_suplier`, `total_harga`, `tanggal_transaksi`) VALUES
('TRXP_031223213016', 'SP_031223125633', 200000, '2023-12-03 14:30:16'),
('TRXP_031223213643', 'SP_031223125807', 600000, '2023-12-03 14:36:43'),
('TRXP_111223173007', 'SP_031223125807', 0, '2023-12-11 10:30:07'),
('TRXP_111223173408', 'SP_031223125807', 0, '2023-12-11 10:34:08'),
('TRXP_111223173502', 'SP_031223125807', 0, '2023-12-11 10:35:02'),
('TRXP_111223173749', 'SP_031223125807', 10000, '2023-12-11 10:37:49'),
('TRXP_111223202219', 'SP_031223125633', 2000, '2023-12-11 13:22:19'),
('TRXP_111223202406', 'SP_031223125633', 1, '2023-12-11 13:24:06'),
('TRXP_111223202721', 'SP_031223125633', 1, '2023-12-11 13:27:21'),
('TRXP_181223122041', 'SUP2400a309', 2000, '2023-12-18 05:20:41');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_penjualan`
--

CREATE TABLE `transaksi_penjualan` (
  `kode_transaksi` char(17) NOT NULL,
  `id_user` int(11) NOT NULL,
  `total_harga` int(11) NOT NULL DEFAULT 0,
  `pembayaran` int(11) NOT NULL DEFAULT 0,
  `kembalian` int(11) NOT NULL DEFAULT 0,
  `tanggal_transaksi` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi_penjualan`
--

INSERT INTO `transaksi_penjualan` (`kode_transaksi`, `id_user`, `total_harga`, `pembayaran`, `kembalian`, `tanggal_transaksi`) VALUES
('TRX0f1c80e9', 1, 11000, 11000, 0, '2023-12-11 13:25:12'),
('TRX109846ef', 1, 33000, 50000, 17000, '2023-12-11 04:57:18'),
('TRX54850e13', 1, 0, 322, 322, '2023-12-15 16:55:06'),
('TRXa2d8a54b', 1, 11000, 11000, 0, '2023-12-15 16:58:49'),
('TRXJ_031223213517', 8, 500000, 500000, 0, '2023-12-03 14:35:17'),
('TRXJ_031223214413', 8, 40083, 40083, 0, '2023-12-03 14:44:13'),
('TRXJ_051223140228', 8, 11000, 11000, 0, '2023-12-05 07:02:28'),
('TRXJ_081223164614', 1, 11000, 11000, 0, '2023-12-08 09:46:14'),
('TRXJ_081223165115', 1, 11000, 20000, 9000, '2023-12-08 09:51:15'),
('TRXJ_081223165442', 1, 11000, 11000, 0, '2023-12-08 09:54:42'),
('TRXJ_081223165828', 1, 11000, 100000, 89000, '2023-12-08 09:58:29'),
('TRXJ_081223171414', 1, 11000, 11000, 0, '2023-12-08 10:14:14'),
('TRXJ_081223221431', 1, 11000, 11000, 0, '2023-12-08 15:14:31'),
('TRXJ_091223102131', 1, 100000, 100000, 0, '2023-12-09 03:21:31'),
('TRXJ_091223112842', 1, 300000, 300000, 0, '2023-12-09 04:28:42'),
('TRXJ_091223241653', 1, 11000, 11000, 0, '2023-12-08 17:16:53'),
('TRXJ_101223021935', 1, 11000, 11000, 0, '2023-12-09 19:19:35'),
('TRXJ_101223113826', 1, 429000, 500000, 71000, '2023-12-10 04:38:26'),
('TRXJ_101223120942', 1, 11000, 11000, 0, '2023-12-10 05:09:42'),
('TRXJ_101223132318', 1, 11000, 11000, 0, '2023-12-10 06:23:18'),
('TRXJ_101223132336', 1, 11000, 1100, -9900, '2023-12-10 06:23:36'),
('TRXJ_101223175934', 1, 11000, 11000, 0, '2023-12-10 10:59:35'),
('TRXJ_101223234824', 1, 165000, 165000, 0, '2023-12-10 16:48:24');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `no_ktp` char(16) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `alamat` varchar(70) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('owner','kasir') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `no_ktp`, `nama`, `alamat`, `username`, `password`, `role`) VALUES
(1, '31293102931', 'Muhammad Nor Kholit', 'bataan,bondowoso', 'muhammad', 'muhammad', 'owner'),
(6, '351111145678', 'Akbar Maulidi R', 'Jl. Diponegoro', 'user12 edit', '123', 'kasir'),
(8, '3511114567', 'Ilham Nur', 'Kotakulon', 'user19', '1234', 'kasir');

-- --------------------------------------------------------

--
-- Structure for view `data_jenis_penjualan`
--
DROP TABLE IF EXISTS `data_jenis_penjualan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `data_jenis_penjualan`  AS SELECT `obat`.`kode_obat` AS `kode_obat`, `obat`.`nama_obat` AS `nama_obat`, `bentuk_sediaan_obat`.`nama_bentuk_sediaan` AS `satuan`, `jenis_penjualan`.`total` AS `total`, `jenis_penjualan`.`harga` AS `harga` FROM ((`jenis_penjualan` join `bentuk_sediaan_obat` on(`jenis_penjualan`.`id_bentuk_sediaan` = `bentuk_sediaan_obat`.`id`)) join `obat` on(`jenis_penjualan`.`kode_obat` = `obat`.`kode_obat`)) ;

-- --------------------------------------------------------

--
-- Structure for view `data_obat`
--
DROP TABLE IF EXISTS `data_obat`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `data_obat`  AS SELECT `obat`.`kode_obat` AS `kode_obat`, `obat`.`nama_obat` AS `nama_obat`, `obat`.`jumlah_obat` AS `jumlah_obat`, `kategori`.`nama_kategori` AS `nama_kategori`, `bentuk_sediaan_obat`.`nama_bentuk_sediaan` AS `satuan`, `obat`.`tanggal_dibuat` AS `tanggal_dibuat`, `obat`.`aturan_pakai` AS `aturan_pakai` FROM ((`obat` join `kategori` on(`obat`.`id_kategori` = `kategori`.`id`)) join `bentuk_sediaan_obat` on(`obat`.`id_bentuk_sediaan` = `bentuk_sediaan_obat`.`id`)) ;

-- --------------------------------------------------------

--
-- Structure for view `data_stok_obat`
--
DROP TABLE IF EXISTS `data_stok_obat`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `data_stok_obat`  AS SELECT `stok_obat`.`id` AS `id`, `obat`.`kode_obat` AS `kode_obat`, `obat`.`nama_obat` AS `nama_obat`, `stok_obat`.`jumlah_obat` AS `jumlah_obat`, `stok_obat`.`harga_beli` AS `harga_beli`, `stok_obat`.`tanggal_kadaluarsa` AS `tanggal_kadaluarsa`, `stok_obat`.`tanggal_masuk` AS `tanggal_masuk`, CASE WHEN cast(`stok_obat`.`tanggal_kadaluarsa` as date) > current_timestamp() THEN 0 ELSE 1 END AS `status_kadaluarsa` FROM ((`stok_obat` join `supplier` on(`stok_obat`.`kode_suplier` = `supplier`.`kode_suplier`)) join `obat` on(`stok_obat`.`kode_obat` = `obat`.`kode_obat`)) ;

-- --------------------------------------------------------

--
-- Structure for view `laporan_pembelian`
--
DROP TABLE IF EXISTS `laporan_pembelian`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `laporan_pembelian`  AS SELECT `transaksi_pembelian`.`kode_transaksi` AS `kode_transaksi`, `supplier`.`nama_suplier` AS `nama_suplier`, sum(`detail_pembelian`.`qty`) AS `jumlah_obat`, `transaksi_pembelian`.`total_harga` AS `total_harga`, `transaksi_pembelian`.`tanggal_transaksi` AS `tanggal_transaksi` FROM ((`transaksi_pembelian` join `supplier` on(`transaksi_pembelian`.`kode_suplier` = `supplier`.`kode_suplier`)) join `detail_pembelian` on(`transaksi_pembelian`.`kode_transaksi` = `detail_pembelian`.`kode_transaksi`)) GROUP BY `transaksi_pembelian`.`kode_transaksi` ;

-- --------------------------------------------------------

--
-- Structure for view `laporan_pendapatan`
--
DROP TABLE IF EXISTS `laporan_pendapatan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `laporan_pendapatan`  AS SELECT sum(`transaksi_penjualan`.`total_harga`) AS `pendapatan`, date_format(`transaksi_penjualan`.`tanggal_transaksi`,'%Y-%m-%d') AS `tanggal` FROM `transaksi_penjualan` GROUP BY date_format(`transaksi_penjualan`.`tanggal_transaksi`,'%Y-%m-%d') ;

-- --------------------------------------------------------

--
-- Structure for view `laporan_pengeluaran`
--
DROP TABLE IF EXISTS `laporan_pengeluaran`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `laporan_pengeluaran`  AS SELECT `pengeluaran`.`id` AS `id`, `users`.`nama` AS `nama`, `pengeluaran`.`keterangan` AS `keterangan`, `pengeluaran`.`total_pengeluaran` AS `jumlah_pengeluaran`, `pengeluaran`.`tanggal_pengeluaran` AS `tanggal_pengeluaran` FROM (`pengeluaran` join `users` on(`pengeluaran`.`id_user` = `users`.`id`)) ;

-- --------------------------------------------------------

--
-- Structure for view `laporan_penjualan`
--
DROP TABLE IF EXISTS `laporan_penjualan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `laporan_penjualan`  AS SELECT `users`.`nama` AS `nama_pengguna`, `transaksi_penjualan`.`kode_transaksi` AS `kode_transaksi`, sum(`detail_penjualan`.`qty`) AS `total_obat`, `transaksi_penjualan`.`total_harga` AS `total_harga`, `transaksi_penjualan`.`pembayaran` AS `pembayaran`, `transaksi_penjualan`.`kembalian` AS `kembalian`, `transaksi_penjualan`.`tanggal_transaksi` AS `tanggal_transaksi` FROM ((`transaksi_penjualan` join `users` on(`transaksi_penjualan`.`id_user` = `users`.`id`)) join `detail_penjualan` on(`transaksi_penjualan`.`kode_transaksi` = `detail_penjualan`.`kode_transaksi`)) GROUP BY `detail_penjualan`.`kode_transaksi` ;

-- --------------------------------------------------------

--
-- Structure for view `laporan_stok`
--
DROP TABLE IF EXISTS `laporan_stok`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `laporan_stok`  AS SELECT `detail_penjualan`.`kode_obat` AS `kode_obat`, `obat`.`nama_obat` AS `nama_obat`, `detail_penjualan`.`qty` AS `obat_terjual`, `transaksi_penjualan`.`tanggal_transaksi` AS `tanggal_transaksi` FROM ((`detail_penjualan` join `transaksi_penjualan` on(`detail_penjualan`.`kode_transaksi` = `transaksi_penjualan`.`kode_transaksi`)) join `obat` on(`detail_penjualan`.`kode_obat` = `obat`.`kode_obat`)) ;

-- --------------------------------------------------------

--
-- Structure for view `printerview`
--
DROP TABLE IF EXISTS `printerview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `printerview`  AS SELECT `transaksi_penjualan`.`kode_transaksi` AS `kode_transaksi`, `users`.`username` AS `username`, `transaksi_penjualan`.`tanggal_transaksi` AS `tanggal_transaksi`, `obat`.`nama_obat` AS `nama_obat`, `detail_penjualan`.`qty` AS `qty`, `detail_penjualan`.`harga` AS `harga`, `detail_penjualan`.`subtotal` AS `subtotal`, `transaksi_penjualan`.`total_harga` AS `total_harga`, `transaksi_penjualan`.`pembayaran` AS `pembayaran`, `transaksi_penjualan`.`kembalian` AS `kembalian` FROM (((`transaksi_penjualan` join `detail_penjualan` on(`detail_penjualan`.`kode_transaksi` = `transaksi_penjualan`.`kode_transaksi`)) join `users` on(`users`.`id` = `transaksi_penjualan`.`id_user`)) join `obat` on(`obat`.`kode_obat` = `detail_penjualan`.`kode_obat`)) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bentuk_sediaan_obat`
--
ALTER TABLE `bentuk_sediaan_obat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `detail_pembelian`
--
ALTER TABLE `detail_pembelian`
  ADD PRIMARY KEY (`id`),
  ADD KEY `kode_obat` (`kode_obat`),
  ADD KEY `detail_pembelian_ibfk_2` (`kode_transaksi`);

--
-- Indexes for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `detail_penjualan_ibfk_1` (`kode_obat`),
  ADD KEY `detail_penjualan_ibfk_2` (`kode_transaksi`);

--
-- Indexes for table `jenis_penjualan`
--
ALTER TABLE `jenis_penjualan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `jenis_penjualan_ibfk_1` (`kode_obat`),
  ADD KEY `id_bentuk_sediaan` (`id_bentuk_sediaan`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `laporan`
--
ALTER TABLE `laporan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `obat`
--
ALTER TABLE `obat`
  ADD PRIMARY KEY (`kode_obat`),
  ADD KEY `id_kategori` (`id_kategori`),
  ADD KEY `bentuk_sediaan_ibfk_!` (`id_bentuk_sediaan`);

--
-- Indexes for table `pengeluaran`
--
ALTER TABLE `pengeluaran`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_suplier` (`kode_suplier`),
  ADD KEY `id_obat` (`kode_obat`);

--
-- Indexes for table `shift`
--
ALTER TABLE `shift`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `stok_obat`
--
ALTER TABLE `stok_obat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `stok_obat_ibfk_1` (`kode_obat`),
  ADD KEY `suplier_fk_2` (`kode_suplier`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`kode_suplier`);

--
-- Indexes for table `transaksi_pembelian`
--
ALTER TABLE `transaksi_pembelian`
  ADD PRIMARY KEY (`kode_transaksi`),
  ADD KEY `suplier_fk_1` (`kode_suplier`);

--
-- Indexes for table `transaksi_penjualan`
--
ALTER TABLE `transaksi_penjualan`
  ADD PRIMARY KEY (`kode_transaksi`),
  ADD KEY `transaksi_penjualan_ibfk_1` (`id_user`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bentuk_sediaan_obat`
--
ALTER TABLE `bentuk_sediaan_obat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `detail_pembelian`
--
ALTER TABLE `detail_pembelian`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `jenis_penjualan`
--
ALTER TABLE `jenis_penjualan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `laporan`
--
ALTER TABLE `laporan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `pengeluaran`
--
ALTER TABLE `pengeluaran`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `penjualan`
--
ALTER TABLE `penjualan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=91;

--
-- AUTO_INCREMENT for table `shift`
--
ALTER TABLE `shift`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `stok_obat`
--
ALTER TABLE `stok_obat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_pembelian`
--
ALTER TABLE `detail_pembelian`
  ADD CONSTRAINT `detail_pembelian_ibfk_1` FOREIGN KEY (`kode_obat`) REFERENCES `obat` (`kode_obat`),
  ADD CONSTRAINT `detail_pembelian_ibfk_2` FOREIGN KEY (`kode_transaksi`) REFERENCES `transaksi_pembelian` (`kode_transaksi`);

--
-- Constraints for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD CONSTRAINT `detail_penjualan_ibfk_1` FOREIGN KEY (`kode_obat`) REFERENCES `obat` (`kode_obat`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_penjualan_ibfk_2` FOREIGN KEY (`kode_transaksi`) REFERENCES `transaksi_penjualan` (`kode_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `jenis_penjualan`
--
ALTER TABLE `jenis_penjualan`
  ADD CONSTRAINT `bentuk_sediaan` FOREIGN KEY (`id_bentuk_sediaan`) REFERENCES `bentuk_sediaan_obat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `jenis_penjualan_ibfk_1` FOREIGN KEY (`kode_obat`) REFERENCES `obat` (`kode_obat`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `obat`
--
ALTER TABLE `obat`
  ADD CONSTRAINT `bentuk_sediaan_ibfk_!` FOREIGN KEY (`id_bentuk_sediaan`) REFERENCES `bentuk_sediaan_obat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `obat_ibfk_1` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id`);

--
-- Constraints for table `pengeluaran`
--
ALTER TABLE `pengeluaran`
  ADD CONSTRAINT `pengeluaran_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

--
-- Constraints for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_fk1` FOREIGN KEY (`kode_obat`) REFERENCES `obat` (`kode_obat`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `penjualan_fk2` FOREIGN KEY (`kode_suplier`) REFERENCES `supplier` (`kode_suplier`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `shift`
--
ALTER TABLE `shift`
  ADD CONSTRAINT `shift_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

--
-- Constraints for table `stok_obat`
--
ALTER TABLE `stok_obat`
  ADD CONSTRAINT `stok_obat_ibfk_1` FOREIGN KEY (`kode_obat`) REFERENCES `obat` (`kode_obat`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `suplier_fk_2` FOREIGN KEY (`kode_suplier`) REFERENCES `supplier` (`kode_suplier`);

--
-- Constraints for table `transaksi_pembelian`
--
ALTER TABLE `transaksi_pembelian`
  ADD CONSTRAINT `suplier_fk_1` FOREIGN KEY (`kode_suplier`) REFERENCES `supplier` (`kode_suplier`);

--
-- Constraints for table `transaksi_penjualan`
--
ALTER TABLE `transaksi_penjualan`
  ADD CONSTRAINT `transaksi_penjualan_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
