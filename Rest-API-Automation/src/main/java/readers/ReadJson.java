package readers;

import models.Category;
import models.Pet;
import models.Tag;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.nio.file.Path;

public class ReadJson {

    public ReadJson() { }

    public static String readJsonFile(Path path) {
        JSONParser parser = new JSONParser();
        String jsonBody = null;

        try {
            Object object = parser.parse(new FileReader(path.toAbsolutePath().toString()));
            JSONObject jsonObject = (JSONObject) object;
            jsonBody = jsonObject.toJSONString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonBody;
    }

    public static String setPostJsonValue(String jsonObject, Pet pet, Category category, Tag tag) {
        return jsonObject
                .replace("{PHOTO}", pet.getPhoto())
                .replace("{PET_NAME}", pet.getName())
                .replace("{ID}", String.valueOf(pet.getId()))
                .replace("{STATUS}", pet.getStatus())
                .replace("{CATEGORY_NAME}", category.getName())
                .replace("{CATEGORY_ID}", String.valueOf(category.getId()))
                .replace("{TAGS_NAME}", tag.getName())
                .replace("{TAGS_ID}", String.valueOf(category.getId()));
    }
}
