import java.util.Scanner;

public class StudyKasusSuperFix {
    static int maxMhs = 100;                                // Untuk Max Mahasiswa (Baris dari tabel)
    static String kolom[] = {"NIM", "Nama", "Kode MK", "Nama Mata Kuliah", "SKS"};  
    static String nim[] = new String[maxMhs];               // Array 1D untuk menyimpan nim
    static String nama[] = new String[maxMhs];              // Array 1D untuk menyimpan nama
    static String[][] kodeMK = new String[maxMhs][maxMhs];
    static String namaMK[][] = new String[maxMhs][maxMhs];  // Array dimensi 1 berisi Max Mahasiswa (Baris) array dimensi 2 untuk KodeMK dan Nama Mata Kuliah 
    static int totalSKS[] = new int[maxMhs];                // Array untuk menyimpan jumlah Total SKS per Mahasiswa
    static int sksMhs[][] = new int[maxMhs][maxMhs];        // Array  2d untuk menyimpan jumkah sks per matkul 
    static int mahasiswa = 0;        
    static int indeksKrs = 0;                               // Agar tidak saling menimpa saat input tambahData 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            int pilihan = 0;    // Untuk menyimpan pilihan pengguna dan mereset pilihan saat pilihan menu dijalankan

            System.out.print("\n=== Sistem Pemantauan KRS Mahasiswa ===");
            System.out.print("\n1. Tambah Data KRS\n2. Tampilkan Daftar KRS Mahasiswa\n3. Analisis Data KRS\n4. Keluar\nPilih Menu: ");
            pilihan = sc.nextInt();
            
            switch (pilihan) {
                case 1:
                    TambahData(sc);
                    break;
                case 2:
                    TampilData(sc);
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
            int sksTotal = 0;
            int sks = 0;
            int i = 0;
            sc.nextLine(); 
            System.out.print("Nama Mahasiswa: ");
            nama[mahasiswa] = sc.nextLine(); 
            System.out.print("NIM: ");
            nim[mahasiswa] = sc.nextLine();
            for(int j = 0; j < kodeMK[i].length; j++){
                System.out.print("Kode Mata Kuliah: ");
                kodeMK[indeksKrs][i] = sc.nextLine();
                
                System.out.print("Nama Mata Kuliah: ");
                namaMK[indeksKrs][i] = sc.nextLine();

                System.out.print("Jumlah SKS (1-3): ");
                sks = sc.nextInt();
                if(sks < 1 || sks > 3){
                    System.out.println("SKS yang anda masukkan tidak valid! masukkan (1-3)");
                    continue;  
                }
                    
                sksMhs[mahasiswa][i] = sks; 
                sksTotal += sks;
                
                totalSKS[mahasiswa] = sksTotal;

                sc.nextLine();
                System.out.print("Tambah mata kuliah lain? (y/t): ");
                String tambah = sc.nextLine();
                if (tambah.equalsIgnoreCase("t")) {
                    System.out.println("Total SKS yang diambil: " + sksTotal);
                    mahasiswa++;
                    indeksKrs++;
                    break;
                }
             
                if(sksTotal >= 24){
                    System.out.println("Total SKS yang diambil: " + sksTotal);
                    break;
                }  
                i++;
            }  
            break;
        }
    }


    public static void TampilData(Scanner sc){
        String searchMahasiswa = "";
        System.out.println("--- Tampil Daftar KRS Mahasiswa ---");
        sc.nextLine();
        System.out.print("Masukkan NIM Mahasiswa: ");
        searchMahasiswa = sc.nextLine();

        System.out.println("\nDaftar KRS: ");
        for(String koloms : kolom){
            System.out.print("\t" + koloms + "\t");
        }
        System.out.println();

        for(int i = 0; i < nim.length; i++){
            int index = -1;
            // if(searchMahasiswa.equalsIgnoreCase(nim[i])){
                index = i;
                
                for (int j = 0; j < kodeMK[i].length; j++) {
                    if (kodeMK[i][j] != null && namaMK[i][j] != null) {
                        System.out.print("\t\t" + nim[i] + "\t\t");
                        System.out.print(nama[i] + "\t\t");
                        System.out.print(kodeMK[i][j] + "\t\t");
                        System.out.print(namaMK[i][j] + "\t\t");
                        System.out.print(sksMhs[i][j] + "\n");
                    }
                }
               
                System.out.println();
                System.out.print("Total SKS: " + totalSKS[index]);
            }  
        }   
    // }

    public static void AnalisisData(){
        System.out.println("--- Analisis Data KRS ---");
        int count = 0;                                          // Deklarasi count
        for(int i = 0; i < maxMhs; i++){
            if(totalSKS[i] <= 20 && totalSKS[i] != 0){
                count++;     
            }
        }
    System.out.println("Jumlah Mahasiswa yang mengambil SKS kurang dari 20: " + count);
    }
}