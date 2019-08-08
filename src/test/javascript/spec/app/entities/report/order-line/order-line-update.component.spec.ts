/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ReportTestModule } from '../../../../test.module';
import { OrderLineUpdateComponent } from 'app/entities/report/order-line/order-line-update.component';
import { OrderLineService } from 'app/entities/report/order-line/order-line.service';
import { OrderLine } from 'app/shared/model/report/order-line.model';

describe('Component Tests', () => {
    describe('OrderLine Management Update Component', () => {
        let comp: OrderLineUpdateComponent;
        let fixture: ComponentFixture<OrderLineUpdateComponent>;
        let service: OrderLineService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReportTestModule],
                declarations: [OrderLineUpdateComponent]
            })
                .overrideTemplate(OrderLineUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OrderLineUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OrderLineService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new OrderLine(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.orderLine = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new OrderLine();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.orderLine = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
