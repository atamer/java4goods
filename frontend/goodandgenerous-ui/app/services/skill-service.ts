import {Injectable} from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/Rx';
import {Observable} from 'rxjs/Rx';


@Injectable()
export class SkillService{
   endpoint_url:string = "http://localhost:3000/public/rest/category";

   headers:Headers;
   constructor(private http: Http){
       this.http = http;
   }

   getSkills():Observable<Object>{

       return this.http.get(this.endpoint_url ,{headers:null}).map((res:Response) => res.json());
   }
}
