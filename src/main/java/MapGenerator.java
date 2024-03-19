import java.util.*;
public class MapGenerator {
    private List<Room> rooms = new ArrayList<>();
    private Map<String, List<TileInformation>> tileInformations = new HashMap<>();
    private char [][] map;
    private Random random;
    public MapGenerator(int seed) {
        random = new Random(seed);
        addPlayer();
    }

    public MapGenerator() {
        random = new Random();
        addPlayer();
    }
    public void addPlayer(){
        tileInformations.put("Player",new ArrayList<>());
        tileInformations.get("Player").add(new TileInformation('p', "Player", false));
    }
    public void addTile(Character symbol, String name, Boolean value) {
        if(!tileInformations.containsKey("Monster")) {
            tileInformations.put("tile",new ArrayList<>());
        }
        tileInformations.get("tile").add(new TileInformation(symbol, name, value));

    }
    public void addTile(Character symbol, String name, Boolean value, int difficulty) {
        if(!tileInformations.containsKey("Monster")) {
            tileInformations.put("Monster",new ArrayList<>());
        }
        tileInformations.get("Monster").add(new TileInformation(symbol, name, value, difficulty));
    }
    public void gridGenerator(int width, int height) {
        map = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = 'w';
            }
        }
    }

    public void generateRooms(int roomCount, int minRoomSize, int maxRoomSize) {
        while(roomCount > rooms.size()) {
            int roomWidth = random.nextInt(minRoomSize, maxRoomSize);
            int roomHeight = random.nextInt(minRoomSize, maxRoomSize);
            int x = random.nextInt(1, map.length - roomWidth);
            int y = random.nextInt(1, map[0].length - roomHeight);
            if(!overlap(x, y, roomWidth, roomHeight)) {
                System.out.println(x+" "+  y+" "+ roomWidth+" "+ roomHeight);
                for (int i = y; i < y + roomHeight; i++) {
                    for (int j = x; j < x + roomWidth; j++) {
                        map[i][j] = 'f';
                    }
                }
                Room room = new Room(x, y, roomWidth, roomHeight);
                rooms.add(room);
            }
        }
    }

    private boolean overlap(int x, int y, int width, int height) {
        int startY = Math.max(y - 1, 0);
        int startX = Math.max(x - 1, 0);
        int endY = Math.min(y + height + 1, map.length);
        int endX = Math.min(x + width + 1, map[0].length);
        for (int i = startY; i < endY; i++) {
            for (int j = startX; j < endX; j++) {
                if (map[i][j] != 'w') {
                    return true;
                }
            }
        }
        return false;
    }

    public void generateCorridors() {
        for (int i = 0; i < rooms.size() - 1; i++){
            int centerX1 = rooms.get(i).centerX();
            int centerY1 = rooms.get(i).centerY();
            int centerY2 = rooms.get(i+1).centerY();
            int centerX2 = rooms.get(i+1).centerX();
            int min = Math.min(centerX1, centerX2);
            int max = Math.max(centerX1, centerX2);
                for (int j = min ; j < max; j++) {
                    map[centerY2][j] = 'f';
                }
            min = Math.min(centerY1, centerY2);
            max = Math.max(centerY1, centerY2);
            for (int j = min ; j < max; j++) {
                map[j][centerX1] = 'f';
            }
        }
    }

    public void printMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.print("\n");
        }
    }

    public void generateSpawns(int difficulty) {
        while (difficulty > 0) {
            int roomSpawn = random.nextInt(rooms.size() - 2);
            int x = random.nextInt(rooms.get(roomSpawn).getX(), rooms.get(roomSpawn).getX() + rooms.get(roomSpawn).getWidth());
            int y = random.nextInt(rooms.get(roomSpawn).getY(), rooms.get(roomSpawn).getY() + rooms.get(roomSpawn).getHeight());
            List<TileInformation> tileMonster = tileInformations.get("Monster");
            int randomMonster = random.nextInt(tileMonster.size());
            if(difficulty >= tileMonster.get(randomMonster).getDifficulty()) {
                System.out.println(tileMonster.get(randomMonster).getSymbol());
                if(map[y][x] == 'f') {
                    System.out.println("Hello");
                    map[y][x] = tileMonster.get(randomMonster).getSymbol();
                    difficulty-=tileMonster.get(randomMonster).getDifficulty();
                }
            }
        }
        System.out.println("spawning player");
        int xPlayer = random.nextInt(rooms.get(rooms.size() - 1).getX(), rooms.get(rooms.size() - 1).getX() + rooms.get(rooms.size() - 1).getWidth());
        int yPlayer = random.nextInt(rooms.get(rooms.size() - 1).getY(), rooms.get(rooms.size() - 1).getY() + rooms.get(rooms.size() - 1).getHeight());
        List<TileInformation> tilePlayer = tileInformations.get("Player");
        map[yPlayer][xPlayer] = tilePlayer.get(0).getSymbol();
    }
    @Override
    public String toString(){
        return "to do";
    }

}
