package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName Button
 *
 * @author Dylan
 * @description TODO
 * @createDate 2019-09-18 11:26
 */
public class Button {

    private List<AbstractButton> buttons = new ArrayList<>();

    public List<AbstractButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<AbstractButton> buttons) {
        this.buttons = buttons;
    }
}
