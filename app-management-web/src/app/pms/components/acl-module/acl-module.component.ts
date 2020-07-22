import {Component, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
@Component({
  selector: 'app-acl-module',
  templateUrl: './acl-module.component.html',
  styleUrls: ['./acl-module.component.scss']
})





export class AclModuleComponent implements OnInit {
  isDisabled: boolean;
  queryForm: FormGroup;

  constructor(private http: HttpClient) {
  }


  model =  {"aclModuleName":"","status":0,"parentAclModuleName":""};
  ngOnInit() {

  }
}
