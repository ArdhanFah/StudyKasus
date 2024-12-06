import java.util.Scanner;

public class StudyKasus {
    static int maxMhs = 100;
    static String kolom[] = {"NIM", "Nama", "Kode MK", "Nama Mata Kuliah", "SKS"};
    static String[][] dataMhs = new String[maxMhs][2];
    static String dataKrs[][] = new String[maxMhs][2];
    static int totalSKS[] = new int[maxMhs];
    static int sksMhs[] = new int[maxMhs];
    static int i = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String searchMahasiswa = "";
        
        pilihMenu(sc, searchMahasiswa);
 
    }

    public static void pilihMenu(Scanner sc, String searchMahasiswa){
        while (true) {
            int pilihan = 0;

            System.out.print("\n=== Sistem Pemantauan KRS Mahasiswa ===");
            System.out.print("\n1. Tambah Data KRS\n2. Tampilkan Daftar KRS Mahasiswa\n3. Analisis Data KRS\n4. Keluar\nPilih Menu: ");
            pilihan = sc.nextInt();
            
            switch (pilihan) {
                case 1:
                    TambahData(sc);
                    break;
                case 2:
                    TampilData(sc, searchMahasiswa);
                    break;
                case 3:
                    AnalisisData();
                    break;
                case 4:
                System.out.println("Terima kasih!");
                    return;
                default:
                    System.out.println("Pilihan anda tidak valid! masukkan [1-4]");
                    continue;
            }
        }
    }

    public static void TambahData(Scanner sc){
        System.out.println("--- Tambah Data KRS ---");
        while(true) {
            int sks = 0;
            int sksTotal = 0;
            sc.nextLine(); 
            System.out.print("Nama Mahasiswa: ");
            dataMhs[i][1] = sc.nextLine(); 
            System.out.print("NIM: ");
            dataMhs[i][0] = sc.nextLine();
            while (true) {
                System.out.print("Kode Mata Kuliah: ");
                dataKrs[i][0] = sc.nextLine();
                
                System.out.print("Nama Mata Kuliah: ");
                dataKrs[i][1] = sc.nextLine();

                System.out.print("Jumlah SKS (1-3): ");
                sks = sc.nextInt();

                if(sks < 1 || sks > 3){
                    System.out.println("SKS yang anda masukkan tidak valid! masukkan (1-3)");
                    continue;  
                } else {
                    sksMhs[i] += sks;
                    sksTotal += sks;
                    totalSKS[i] = sksTotal;
                }
                
                sc.nextLine();
                System.out.print("Tambah mata kuliah lain? (y/t): ");
                String tambah = sc.nextLine();
                if (tambah.equalsIgnoreCase("t")) {
                    System.out.println("Total SKS yang diambil: " + totalSKS[i]);
                    i++;
                    break;
                }
            }  
            break;
        }
    }

    public static void TampilData(Scanner sc, String searchMahasiswa){
        System.out.println("--- Tampil Daftar KRS Mahasiswa ---");
        sc.nextLine();
        System.out.print("Masukkan NIM Mahasiswa: ");
        searchMahasiswa = sc.nextLine();

        System.out.println("\nDaftar KRS: ");
        for(int i = 0; i < dataKrs.length; i++){
            int index = -1;
            if(searchMahasiswa.equalsIgnoreCase(dataMhs[i][0])){
                index = i;
                for(String koloms : kolom){
                    System.out.print("\t" + koloms + "\t");
                }
                System.out.println();

                System.out.print("\t" + dataMhs[index][0] + "\t\t" + dataMhs[index][1]); 
                for(int j = 0; j < dataKrs[i].length; j++){
                System.out.print("\t\t" + dataKrs[index][j]);
                }
                System.out.print("\t\t\t\t" + sksMhs[i]);

                System.out.println();
                System.out.print("Total SKS: " + totalSKS[index]);
            } 
        }   
    }

    public static void AnalisisData(){
        System.out.println("--- Analisis Data KRS ---");
        int count = 0;
        for(int i = 0; i < maxMhs; i++){
            if(totalSKS[i] < 20){
                count++;
            }
        }

        System.out.println("Jumlah Mahasiswa yang mengambil SKS kurang dari 20: " + count);
    }
}
