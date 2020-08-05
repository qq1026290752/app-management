import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {PageHelper,ResultHelper} from '../../../utils';

export interface AdminAclModuleResponse {
  aclModuleId: number;
  aclModuleName: string;
  parentAclModuleId: number;
  parentAclModuleName: string;
  status: Number;
  createTime: Date;
  moduleSeq: number;
  moduleRemark: string;
}

@Component({
  selector: 'app-acl-module',
  templateUrl: './acl-module.component.html',
  styleUrls: ['./acl-module.component.scss']
})

export class AclModuleComponent implements OnInit {
  isDisabled: boolean;
  queryForm: FormGroup;
  dataSource: AdminAclModuleResponse[] = [];
  constructor(private http: HttpClient,
              private fb: FormBuilder,) {

  }

  ngOnInit() {
    this.queryForm = this.fb.group({
      aclModuleName:[''],
      aclModuleStatus:[-1],
      parentAclModuleName:[''],
    });
    this.http.post<ResultHelper<PageHelper<AdminAclModuleResponse>>>('/aclModule/list', this.queryForm.value).subscribe((data) => {
      console.log('acl module init table ... ')
      if(data.code === 0){
        this.dataSource =  data.data.records;
      }
      console.log(this.dataSource)
    })
  }
}
