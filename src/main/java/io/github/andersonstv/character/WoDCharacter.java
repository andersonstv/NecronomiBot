package io.github.andersonstv.character;


import io.github.andersonstv.util.DiceUtil;
import io.github.andersonstv.util.FormatUtil;
import io.github.andersonstv.util.ImportUtil;
import org.apache.commons.text.WordUtils;

import java.util.Arrays;
import java.util.Map;

public class WoDCharacter extends GenericCharacter {
    private int[] hp;
    private int[] willpower;

    public WoDCharacter(String charName) {
        super(charName);
        setDefault();
    }
    public void setDefault(){
        attributes = ImportUtil.importMapCSV(ImportUtil.filepath + "DEFAULT_ATTRIBUTES_WoD.csv");
        skills = ImportUtil.importMapCSV(ImportUtil.filepath + "DEFAULT_SKILLS_WoD.csv");
        calculateStats();
    }
    public void calculateStats(){
        hp = new int[]{ attributes.get("size") + attributes.get("stamina"),0};
        willpower = new int[]{attributes.get("composure") + attributes.get("resolve"), 0};
    }
    public String check(String stat){
        if (attributes.containsKey(stat)){
            return attributeCheck(stat);
        } else if(skills.containsKey(stat)){
            return skillCheck(stat);
        } else {
            return "Skill or Attribute not found.";
        }
    }

    @Override
    public void setStat(String statName, int value) {
        super.setStat(statName, value);
        calculateStats();
    }

    public String check(String stat, String secondary){
        int total;
        if (attributes.containsKey(stat)){
            if(attributes.containsKey(secondary)){
                total = attributes.get(stat) + attributes.get(secondary);
                return DiceUtil.wodRoll(total);
            } else if(skills.containsKey(secondary)){
                total = attributes.get(stat) + skills.get(secondary);
                return DiceUtil.wodRoll(total);

            }
        } else if(skills.containsKey(stat)){
            if(attributes.containsKey(secondary)){
                total = skills.get(stat) + attributes.get(secondary);
                return DiceUtil.wodRoll(total);
            } else if(skills.containsKey(secondary)){
                total = skills.get(stat) + skills.get(secondary);
                return DiceUtil.wodRoll(total);
            }
        }
        return "Skill or Attribute Not found.";
    }
    private String skillCheck(String skillName){
        return DiceUtil.wodRoll(skills.get(skillName), 8);
    }
    private String attributeCheck(String attName){
        return DiceUtil.wodRoll(attributes.get(attName));
    }

    @Override
    public String toString() {
        String sep = FormatUtil.sep;
        StringBuilder sheet = new StringBuilder();
        sheet.append("**Name: ***").append(id).append("*").append(sep);
        sheet.append("**Health: **").append(hp[0]).append("[").append(hp[1]).append("]").append(sep);
        sheet.append("**Willpower: **").append(willpower[0]).append("[").append(willpower[1]).append("]").append(sep);
        sheet.append("**Attributes: **").append(sep);
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
