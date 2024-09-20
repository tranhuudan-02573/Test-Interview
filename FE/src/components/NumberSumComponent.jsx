import React, { useState } from 'react';
import { Button, Form, Space, Tooltip, Input } from 'antd';
import { useQuery } from '@tanstack/react-query';
import axios from 'axios';
import './NumberSum.css'
const formatNumber = (value) => new Intl.NumberFormat().format(value);

const NumericInput = (props) => {
    const { value, onChange } = props;
    const handleChange = (e) => {
        const { value: inputValue } = e.target;
        const reg = /^-?\d*(\.\d*)?$/;
        if (reg.test(inputValue) || inputValue === '' || inputValue === '-') {
            onChange(inputValue);
        }
    };

    const handleBlur = () => {
        let valueTemp = value;
        if (value.charAt(value.length - 1) === '.' || value === '-') {
            valueTemp = value.slice(0, -1);
        }
        onChange(valueTemp.replace(/0*(\d+)/, '$1'));
    };

    const title = value ? (
        <span className="numeric-input-title">{value !== '-' ? formatNumber(Number(value)) : '-'}</span>
    ) : (
        'Nhập một số'
    );

    return (
        <Tooltip trigger={['focus']} title={title} placement="topLeft" overlayClassName="numeric-input">
            <Input
                {...props}
                onChange={handleChange}
                onBlur={handleBlur}
                placeholder="Số cần cộng"
                maxLength={16}
            />
        </Tooltip>
    );
};

const NumberSumComponent = () => {
    const [form] = Form.useForm();
    const [value1, setValue1] = useState('');
    const [value2, setValue2] = useState('');

    const sumNumbers = async (values) => {
        const response = await axios.post('http://localhost:8080/plus-two-number', {
            number1: values.number1,
            number2: values.number2
        });
        return response.data;
    };

    const { refetch, isLoading, isError, error, data } = useQuery({
        queryKey: ['sumNumbers'],
        queryFn: () => sumNumbers(form.getFieldsValue()),
        enabled: false,
    });

    const onFinish = () => {
        refetch();
    };

    const onReset = () => {
        form.resetFields();
        setValue1('');
        setValue2('');
    };

    return (
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '100vh', backgroundColor: '#f0f2f5' }}>
            <Form
                form={form}
                name="number-sum"
                onFinish={onFinish}
                style={{ maxWidth: 600, width: '100%', padding: '24px', backgroundColor: 'white', borderRadius: '8px', boxShadow: '0 2px 8px rgba(0,0,0,0.15)' }}
                labelCol={{ span: 8 }}
                wrapperCol={{ span: 16 }}
            >
                <h1 style={{ textAlign: 'center' }} >Tính tổng hai số</h1>
                <Form.Item
                    name="number1"
                    label="Số thứ nhất"
                    rules={[{ required: true, message: 'Vui lòng nhập số thứ nhất!' }]}
                >
                    <NumericInput value={value1} onChange={setValue1} />
                </Form.Item>

                <Form.Item
                    name="number2"
                    label="Số thứ hai"
                    rules={[{ required: true, message: 'Vui lòng nhập số thứ hai!' }]}
                >
                    <NumericInput value={value2} onChange={setValue2} />
                </Form.Item>

                <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                    <Space>
                        <Button type="primary" htmlType="submit" loading={isLoading}>
                            Tính tổng
                        </Button>
                        <Button htmlType="button" onClick={onReset}>
                            Đặt lại
                        </Button>
                    </Space>
                </Form.Item>

                {isError && (
                    <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                        <div style={{ color: 'red' }}>Lỗi: {error.message}</div>
                    </Form.Item>
                )}

                {data && (
                    <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                        <div style={{ color: 'green' }}>Kết quả: {data.result}</div>
                    </Form.Item>
                )}
            </Form>
        </div>
    );
};

export default NumberSumComponent;
