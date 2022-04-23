package io.github.andersonstv.character;

import io.github.andersonstv.util.DiceUtil;
import io.github.andersonstv.util.FormatUtil;
import io.github.andersonstv.util.ImportUtil;
import org.apache.commons.text.WordUtils;

import java.util.Map;

public class CoCCharacter extends GenericCharacter {

    public CoCCharacter(String charName){
        super(charName);
        setDefault();
    }
    public void setDefault() {
        attributes = ImportUtil.importMapCSV(ImportUtil.filepath + "DEFAULT_ATTRIBUTES_CoC.csv");
        skills = ImportUtil.importMapCSV(ImportUtil.filepath + "DEFAULT_SKILLS_CoC.csv");

    }

    public String check(String stat){
        if(this.attributes.containsKey(stat)){
            return attributeCheck(stat);
        } else if(skills.containsKey(stat)){
            return skillCheck(stat);
        } else {
            return "Skill or Attribute not found.";
        }
    }
    public String check(String stat, String statSecondary){
        return check(stat);
    }
    public String skillCheck(String skillName){
        return DiceUtil.cocRoll(skills.get(skillName));
    }

    public String attributeCheck(String attName){
        return DiceUtil.cocRoll(attributes.get(attName));
    }

    @Override
    public String toString() {
        String sep = FormatUtil.sep;
        StringBuilder sheet = new StringBuilder();
        sheet.append("**Name: ***").append(id).append("*").append(sep);
        sheet.append("**Characteristics: **").append(sep);
        for (Map.Entry<String, Integer> entry : attributes.entrySet()) {
            sheet.append(WordUtils.capitalize(entry.getKey())).append(": ").append(entry.getValue()).append(sep);
        }
        sheet.append("**Skills: **").append(sep);
        for (Map.Entry<String, Integer> entry : skills.entrySet()) {
            sheet.append(WordUtils.capitalizeFully(entry.getKey())).append(": ").append(entry.getValue()).append(sep);
        }
        return sheet.toString();
    }
}
