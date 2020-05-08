import { MatIconRegistry } from "@angular/material/icon";
import { DomSanitizer } from "@angular/platform-browser";

export const loadIconResources = (ir:MatIconRegistry,ds:DomSanitizer) =>{
  ir.addSvgIcon("",ds.bypassSecurityTrustHtml(""));
  ir.addSvgIconSet(ds.bypassSecurityTrustResourceUrl("https://at.alicdn.com/t/font_1252681_ahqxtrwx2hm.css"))
}
