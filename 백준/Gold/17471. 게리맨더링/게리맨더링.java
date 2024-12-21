import java.io.*;
import java.util.*;

public class Main{
	private static int sectorCount;
	private static int maxPopCount = 0;
	private static int result = Integer.MAX_VALUE;
	private static boolean canSeparate = false;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Sector[] sectors = getInput(br);
		int result = solution(sectors);
		System.out.println(result);
	}

	private static int solution(Sector[] sectors){
		boolean[] partA = new boolean[sectorCount + 1];

		for(int i = 0; i < sectorCount; i++){
			dfs(sectors, partA, i + 1, 0);
		}

		return canSeparate ? result : -1;
	}

	// 두 선거구로 나누는 함수. 1부터 체크하고 하나씩 추가될 때마다 확인 후 반환
	private static void dfs(Sector[] sectors, boolean[] partA, int newSector, int total){
		Sector sector = sectors[newSector];
		partA[newSector] = true;
		total += sector.getPopulation();

		if(isValid(sectors, partA)){
			result = Math.min(result, Math.abs(maxPopCount - total * 2));
			canSeparate = true;
		}

		for(int i = newSector + 1; i < sectorCount; i++){
			dfs(sectors, partA, i, total);
		}

		partA[newSector] = false;
	}

	// 조건 만족 체크 함수. 빠지는 선거구의 주변 구역 체크?
	private static boolean isValid(Sector[] sectors, boolean[] partA){
		boolean[] visited = new boolean[sectorCount + 1];
		int seongeogu = 0;

		for(int i = 1; i <= sectorCount; i++){
			if(!visited[i]){
				seongeogu++;
				Queue<Integer> q = new LinkedList<>();
				q.add(i);
				while(!q.isEmpty()){
					int s = q.poll();
					visited[s] = true;
					for(int sector: sectors[s].getNeighbors()){
						if(partA[s] == partA[sector] && !visited[sector]){
							q.add(sector);
						}
					}
				}
			}
		}

		return seongeogu == 2;
	}

	private static Sector[] getInput(BufferedReader br) throws IOException{
		sectorCount = Integer.parseInt(br.readLine());
		Sector[] sectors = new Sector[sectorCount + 1];

		String[] populations = br.readLine().split(" ");
		for(int i = 0; i < sectorCount; i++){
			int pop = Integer.parseInt(populations[i]);
			maxPopCount += pop;
			Sector sector = new Sector(pop);

			String[] input = br.readLine().split(" ");
			int neighborCount = Integer.parseInt(input[0]);
			for(int j = 0; j < neighborCount; j++){
				sector.addNeighbor(Integer.parseInt(input[j+1]));
			}

			sectors[i + 1] = sector;
		}

		return sectors;
	}
}

class Sector{
	int population;
	List<Integer> neighbors;

	public Sector(int population){
		this.population = population;
		neighbors = new LinkedList<>();
	}

	public int getPopulation() {
		return this.population;
	}

	public List<Integer> getNeighbors(){
		return this.neighbors;
	}

	public void addNeighbor(int sectorNum){
		neighbors.add(sectorNum);
	}
}
