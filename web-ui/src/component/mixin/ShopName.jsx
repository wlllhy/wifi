import { Button, Modal, Form, Input, Radio } from 'antd';
const FormItem = Form.Item;
import React from 'react';
const CollectionCreateForm = Form.create()(
    (props) => {
        const { visible, onCancel, onCreate, form } = props;
        const { getFieldDecorator } = form;
        return (
            <Modal
                visible={visible}
                title="添加学员"
                okText="添加"
                onCancel={onCancel}
                onOk={onCreate}
            >
                <Form layout="vertical">
                    <FormItem label="mac地址">
                        {getFieldDecorator('shop_addr', {
                            rules: [{ required: true, message: '请输入mac地址' }],
                        })(
                            <Input />
                        )}
                    </FormItem>
                    <FormItem label="学员姓名">
                        {getFieldDecorator('shop_name')(<Input type="text" />)}
                    </FormItem>
                    <FormItem label="部门">
                        {getFieldDecorator('shop_manager')(<Input type="text" />)}
                    </FormItem>
                    <FormItem label="学号">
                        {getFieldDecorator('shop_telephone')(<Input type="text" />)}
                    </FormItem>
                </Form>
            </Modal>
        );
    }
);

export default CollectionCreateForm;