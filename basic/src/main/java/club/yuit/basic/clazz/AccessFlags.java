package club.yuit.basic.clazz;

import club.yuit.basic.clazz.AccessFlag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author: yuit
 * @date: 2023/05/30 20:10
 */
@Getter
@Setter
public class AccessFlags {

   private int value;
   private String hex;

   List<AccessFlag> accessFlags;

   @Override
   public String toString() {
      return AccessFlagsUtils.convertToStrings(this.value);
   }
}
