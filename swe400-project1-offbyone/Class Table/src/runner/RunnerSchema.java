package runner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import data.Nails;
import data.PowerTools;
import data.StripNails;
import data.Tools;
import datasource.Database;
import domain.nail.NailMapper;
import domain.power_tool.PowerToolMapper;
import domain.strip_nail.StripNailMapper;
import domain.tool.ToolMapper;

/**
 * Loads or ReLoads the tables into database.
 *
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class RunnerSchema {

    private static final File[] SQL_SCHEMA_FILES = {
        new File("sql/drop.sql"),
        new File("sql/inventory_item.sql"),
        new File("sql/tool.sql"),
        new File("sql/power_tool.sql"),
        new File("sql/fastener.sql"),
        new File("sql/nail.sql"),
        new File("sql/stripNails.sql"),
        new File("sql/powertool_stripnail.sql")
    };

    private static final File[] SQL_RELATION_FILES = {
        new File("sql/relations.sql")
    };

    /**
     *  Loads or ReLoads the tables into database.
     * @param args nothing.
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws FileNotFoundException FileNotFoundException
     * @throws IOException IOException
     * @throws SQLException SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException, SQLException {
        System.out.print("Reconstructing schema... ");
        executeSql(SQL_SCHEMA_FILES);
        System.out.println("Done.");

        System.out.print("Inserting data... ");
        makeData();
        System.out.println("Done.");

        System.out.print("Inserting relations... ");
        executeSql(SQL_RELATION_FILES);
        System.out.println("Done.");
    }

    private static void executeSql(File[] sqlFiles) throws ClassNotFoundException, FileNotFoundException, IOException, SQLException {
        ArrayList<String> operations = new ArrayList<String>();

        for(File file: sqlFiles) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while(line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            operations.add(stringBuilder.toString());
        }

        for(String operation: operations) {
            PreparedStatement statement = Database.prepareStatement(operation);
            statement.execute();
            statement.close();
        }
    }

    private static void makeData() throws ClassNotFoundException, SQLException {
    	 for(Tools data: Tools.values()) {
             ToolMapper.create(data.getUpc(), data.getManufacturerID(), data.getPrice(), data.getDescription());
         }

         for(PowerTools data: PowerTools.values()) {
             PowerToolMapper.create(data.getUpc(), data.getManufacturerID(), data.getPrice(), data.getDescription(), data.isBatteryPowered());
         }

         for(Nails data: Nails.values()) {
             NailMapper.create(data.getUpc(), data.getManufacturerID(), data.getPrice(), data.getLength(), data.getNumberInBox());
         }

         for(StripNails data: StripNails.values()) {
             StripNailMapper.create(data.getUpc(), data.getManufacturerID(), data.getPrice(), data.getLength(), data.getNumberInStrip());
         }
    }

}
