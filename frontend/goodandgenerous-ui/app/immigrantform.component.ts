import 'rxjs/add/operator/switchMap';
import { Component, OnInit }      from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location }               from '@angular/common';
import { Immigrant }    from './immigrant';
import { SkillService }    from './services/skill-service';
import { Rating }    from './components/rating.component';



@Component({
  moduleId: module.id,
  selector: 'immigrant-form',
  templateUrl: 'immigrantform.component.html',
  providers: [SkillService]
})
export class ImmigrantForm implements OnInit {


  //value used with custom icons demo above
  private rateValueExample1: number = 5;
  //value used with custom icons demo above
  private rateValueExample2: number = 2;
  //the maximum allowed value
  private maxRateValue: number = 10;
  //contains the current value entred by the user
  private currentRate: number = 7;
  //make the rating component readonly
  private isRatingReadonly: boolean = false;
  private overStar: number;
  private ratingPercent: number;
  public rating: any;


  private ratingStatesItems: any = [
    { stateOn: 'glyphicon-star', stateOff: 'glyphicon-ok-circle' },
    { stateOn: 'glyphicon-heart', stateOff: 'glyphicon-star-empty' },
    { stateOn: 'glyphicon-heart', stateOff: 'glyphicon-ban-circle' },
    { stateOn: 'glyphicon-heart', stateOff: 'glyphicon-ban-circle' },
    { stateOn: 'glyphicon-heart', stateOff: 'glyphicon-ban-circle' }
  ];

  constructor(private skillservice: SkillService) { }


  ngOnInit(): void {
    this.skillservice.getSkills().subscribe(
      comments => {

        console.log(comments);
      },
      err => {
        // Log errors if any
        console.log(err);
      });

  }

  powers = ['Really Smart', 'Super Flexible',
    'Super Hot', 'Weather Changer'];

  model = new Immigrant();

  submitted = false;

  onSubmit() { this.submitted = true; }

  // TODO: Remove this when we're done
  get diagnostic() { return JSON.stringify(this.model); }

  newHero() {
    this.model = new Immigrant();
  }
  //////// NOT SHOWN IN DOCS ////////

  // Reveal in html:
  //   Name via form.controls = {{showFormControls(heroForm)}}
  showFormControls(form: any) {

    return form && form.controls['name'] &&
      form.controls['name'].value; // Dr. IQ
  }

  //reset the rating value to null
  private resetRatingStar() {
    this.overStar = null;
  }
  //call this method when over a star
  private overStarDoSomething(value: number): void {
    this.overStar = value;
    this.ratingPercent = 100 * (value / this.maxRateValue);
  };

}


/*
Copyright 2016 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/
